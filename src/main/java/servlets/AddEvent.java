package servlets;

import hibernate.EventsEntity;
import hibernate.Main;
import hibernate.UsersEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.securityfilter.realm.SimplePrincipal;
import security.Constants;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: TravelTogether
 * Date: 02/05/2014
 * Time: 07:50 PM
 */
public class AddEvent extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAll(req, resp);
    }



    private void doAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UsersEntity dbuser = null;
        Session session = Main.getSession();
        Transaction tx = null;
        EventsEntity e = new EventsEntity();
        if (req.getParameter("optionsRadios").equals("Traveling")) {
            e.setTitle("Traveling from " + req.getParameter("From") + " to " + req.getParameter("To") + " ." + req.getParameter("Title"));
            e.setClazz("event-success");
        } else {
            e.setTitle("Staying in " + req.getParameter("From") + " ." + req.getParameter("Title"));
            e.setClazz("event-info");
        }
        e.setUrl(req.getParameter("URL"));
        String start = req.getParameter("StartDay") + " " + req.getParameter("StartHour");
        String end = req.getParameter("EndDay") + " " + req.getParameter("EndHour");
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try {
            e.setStart(date.parse(start).toInstant().toEpochMilli());
            e.setEnd(date.parse(end).toInstant().toEpochMilli());
        } catch (ParseException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
        }
        String x=req.getParameter("X");
        e.setX(Float.parseFloat(req.getParameter("X")));
        e.setY(Float.parseFloat(req.getParameter("Y")));
        session = Main.getSession();
        try {


            tx = session.beginTransaction();
            String hql = "FROM UsersEntity U WHERE U.user = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", ((SimplePrincipal) req.getSession().getAttribute("org.securityfilter.filter.SecurityRequestWrapper.PRINCIPAL")).getName());
            dbuser = (UsersEntity) query.uniqueResult();
            Set<EventsEntity> events = dbuser.getEvents();
            events.add(e);
            dbuser.setEvents(events);
            e.setUser(dbuser);
            session.save(e);
            session.save(dbuser);
            tx.commit();
        } catch (HibernateException ez) {
            if (tx != null) tx.rollback();
            ez.printStackTrace();
            JOptionPane.showMessageDialog(null, ez);
        } finally {
            session.close();
        }
        String body = "<html>" + dbuser.getUser() + " has created a event " + e.toString() + " that matchs with one of your events!!"
                + "<a href=\"" + Constants.WEB_ADDRESS + "\" target=\"_blanck\" > TravelTogether </a></html>";

        List<String> matchingUsersMail = getMatchingUsersMail(dbuser.getId(), e);
        for (String userMail : matchingUsersMail) {
            sendFromGMail(new String[]{userMail}, "New matching events!!", body);
        }

        resp.sendRedirect("calendar?user=" + dbuser.getUser());
    }

    private List<String> getMatchingUsersMail(int id, EventsEntity userEvent) {


        List<String> mails = new ArrayList<>();

        try {
            Session hibernateSession = Main.getSession();
            String hql = "FROM UsersEntity U";
            Query query = hibernateSession.createQuery(hql);
            List<UsersEntity> allUsers = (List<UsersEntity>) query.list();
            for (UsersEntity user : allUsers) {
                if (user.getId() != id) {
                    for (EventsEntity event : user.getEvents()) {

                        if (userEvent.getStart() - event.getStart() < 86400000 &&
                                userEvent.getStart() - event.getStart() > -86400000) {

                            if (userEvent.getTitle().split("\\.")[0].equals(event.getTitle().split("\\.")[0])) {

                                mails.add(user.getEmail());
                                break; //dont want to send multiple mails to each user for each event they share
                            }


                        }


                    }
                }
            }

        } catch (HibernateException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return mails;

    }


    private void sendFromGMail(String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", Constants.SYSTEM_ADDRESS);
        props.put("mail.smtp.password", Constants.MAIL_PASS);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(Constants.SYSTEM_ADDRESS));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (InternetAddress toAddres : toAddress) {
                message.addRecipient(Message.RecipientType.TO, toAddres);
            }

            message.setSubject(subject);
            message.setContent(body, "text/html");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, Constants.SYSTEM_ADDRESS, Constants.MAIL_PASS);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
            JOptionPane.showMessageDialog(null, "adress");
            try {
                System.out.println(Arrays.toString(message.getAllRecipients()));
            } catch (MessagingException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e);
            }
        } catch (MessagingException me) {
            me.printStackTrace();
            JOptionPane.showMessageDialog(null, "messaging");
        }
    }


}
