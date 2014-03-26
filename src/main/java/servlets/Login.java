package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

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
        if ("toto".equals(user) && "toto".equals(pass)) {
            JOptionPane.showMessageDialog(null, "login ok");

            resp.sendRedirect("/Secure/welcome.jsp");
        } else {
            JOptionPane.showMessageDialog(null, "login failed");
            resp.sendRedirect("login.jsp");
        }
    }


}
