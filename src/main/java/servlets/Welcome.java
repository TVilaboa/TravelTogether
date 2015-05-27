package servlets;

import hibernate.Main;
import hibernate.MessageEntity;
import hibernate.UsersEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.securityfilter.realm.SimplePrincipal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: TravelTogether
 * Date: 5/28/14
 * Time: 1:38 PM
 */
public class Welcome extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        messagesToWelcome(req, resp);

    }

    private void messagesToWelcome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/Secure/welcome.jsp");


        req.setAttribute("userMessages", convertToTable(getUserMessages(((SimplePrincipal) req.getSession().getAttribute("org.securityfilter.filter.SecurityRequestWrapper.PRINCIPAL")).getName())));
        req.setAttribute("userSession", ((SimplePrincipal) req.getSession().getAttribute("org.securityfilter.filter.SecurityRequestWrapper.PRINCIPAL")).getName());
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        messagesToWelcome(req, resp);
    }

    private String convertToTable(List<String> messages) {
        String table = "<div class=\"row\"><div class=\"col-md-3\">Sender</div><div class=\"col-md-3\">Message</div><div class=\"col-md-3\">Mail</div><div class=\"col-md-3\">Date</div></div>";
        for (int i = 0; i < messages.size(); i = i + 4) {
            table += "<div class=\"row\"><div class=\"col-md-3\">" + messages.get(i) + "</div><div class=\"col-md-3\">" + messages.get(i + 1) + "</div><div class=\"col-md-3\">" + messages.get(i+2) + "</div><div class=\"col-md-3\">" + messages.get(i+3)  + "</div></div>";
        }
        return table;
    }

    private List<String> getUserMessages(String username) {


        List<String> messages = new ArrayList<>();

        try {
            Session hibernateSession = Main.getSession();
            String hql = "FROM UsersEntity U WHERE U.user = :userName";
            Query query = hibernateSession.createQuery(hql);
            query.setParameter("userName", username);
            Set<MessageEntity> userMessages = ((UsersEntity) query.uniqueResult()).getMessages();

            for (MessageEntity message : userMessages) {
                messages.add(message.getSender());
                messages.add(message.getText());
                messages.add(message.getSenderEmail());
                messages.add(message.getDate().toString());
            }

        } catch (HibernateException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return messages;

    }
}
