package servlets;


import hibernate.EventsEntity;
import hibernate.Main;
import hibernate.UsersEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import security.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Trav
 * Date: 26/03/14
 * Time: 10:11
 */
public class Register extends HttpServlet {

    Connection con;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String user = req.getParameter(Constants.REGISTER_USERNAME_FIELD);
        String pass = req.getParameter(Constants.REGISTER_PASSWORD_FIELD);
        String email = req.getParameter("Email");
        if (email.equals("") || user.equals("") || pass.equals(""))
            JOptionPane.showMessageDialog(null, "Datos ingresados incorrectamente");
        else
            register(user, pass, email, resp);
        resp.sendRedirect("login.jsp");
    }

    private void register(String user, String pass, String email, HttpServletResponse resp) throws IOException {
        Session session = Main.getSession();
        Transaction tx = null;
        EventsEntity e = new EventsEntity(500, "hola", "http://www.example.com/", "event-special", 1363197600000L, 1363629686400L, null);
        try {
            tx = session.beginTransaction();

            session.save(e);
            tx.commit();


        } catch (HibernateException ez) {
            if (tx != null) tx.rollback();
            ez.printStackTrace();
        } finally {
            session.close();
        }
        session = Main.getSession();
        try {
            tx = session.beginTransaction();

            UsersEntity userE = new UsersEntity();
            userE.setUser(user);
            userE.setEmail(email);
            userE.setPass(pass);
            HashSet<EventsEntity> events = new HashSet<>();


            events.add(e);
            userE.setEvents(events);
            session.save(userE);
            HashSet<UsersEntity> users = new HashSet<>();
            users.add(userE);
            e.setUsers(users);
            session.save(e);
            tx.commit();
            JOptionPane.showMessageDialog(null, "Succesfully registered");
        } catch (HibernateException ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }


    }
}
