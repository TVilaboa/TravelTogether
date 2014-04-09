package servlets;

import hibernate.Main;
import hibernate.UsersEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Trav
 * Date: 25/03/14
 * Time: 21:53
 */
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String pass = req.getParameter("password");
        Session session = Main.getSession();
        String hql = "FROM UsersEntity U WHERE U.user = toto1";
        Query query = session.createQuery(hql);
        List results = query.list();
        if (results.isEmpty())
            JOptionPane.showMessageDialog(null, "user doesnt exist");
        else {
            UsersEntity dbuser = (UsersEntity) results.get(0);
            if (dbuser.getPass().equals(pass)) {
                JOptionPane.showMessageDialog(null, "login ok");
                resp.sendRedirect("/Secure/welcome.jsp");
            } else {
                JOptionPane.showMessageDialog(null, "login failed");
                resp.sendRedirect("login.jsp");
            }
        }
    }


}
