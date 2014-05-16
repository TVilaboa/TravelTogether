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
import java.util.Set;

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

        resp.sendRedirect("calendar?user=" + dbuser.getUser());
    }


}
