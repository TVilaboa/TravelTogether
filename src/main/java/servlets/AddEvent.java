package servlets;

import hibernate.EventsEntity;
import hibernate.Main;
import hibernate.UsersEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.securityfilter.realm.SimplePrincipal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        try {
            SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            e.setStart(date.parse(start).toInstant().toEpochMilli());
            e.setEnd(date.parse(end).toInstant().toEpochMilli());
            tx = session.beginTransaction();
            String hql = "FROM UsersEntity U WHERE U.user = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", ((SimplePrincipal) req.getSession().getAttribute("org.securityfilter.filter.SecurityRequestWrapper.PRINCIPAL")).getName());
            UsersEntity dbuser = (UsersEntity) query.uniqueResult();
            hql = "FROM EventsEntity E WHERE E.title = :title";
            //TODO find a way to search for events that have the same from and to.Thing is that ,that info is contained in
            //TODO title with another info. partial search (regex) or create to and from column in table. After that,
            //TODO see if start date is within 24 hours forward and past from event being created, if so, event is the same
            //TODO thing is, that if event is the same, personal info like,rest of title,url ,etc will be lost, so best aproach
            //TODO seems to create to and from, and change many to many realations, to 1 user has many events, and see if they are alike
            e.getUsers().add(dbuser);
            dbuser.getEvents().add(e);
            session.save(e);
            session.save(dbuser);
            tx.commit();
        } catch (HibernateException ez) {
            if (tx != null) tx.rollback();
            ez.printStackTrace();
            JOptionPane.showMessageDialog(null, ez);
        } catch (ParseException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1);
        } finally {
            session.close();
        }

        resp.sendRedirect("calendar");
    }


}
