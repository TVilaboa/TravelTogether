package servlets;


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
import java.sql.DriverManager;
import java.sql.SQLException;

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

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
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
        try {
            tx = session.beginTransaction();
            UsersEntity userE = new UsersEntity();
            userE.setUser(user);
            userE.setEmail(email);
            userE.setPass(pass);
            session.save(userE);
            tx.commit();
            JOptionPane.showMessageDialog(null, "Succesfully registered");
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }


    }
}
