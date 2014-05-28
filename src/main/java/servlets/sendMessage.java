package servlets;

import hibernate.Main;
import hibernate.MessageEntity;
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
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: TravelTogether
 * Date: 5/21/14
 * Time: 11:33 AM
 */
public class SendMessage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Transaction tx = null;
        Session session = Main.getSession();
        UsersEntity dbuser = null;
        try {

            MessageEntity m = new MessageEntity();
            m.setText(req.getParameter("Message"));
            System.out.println(req.getParameter("destinatary"));
            tx = session.beginTransaction();
            String hql = "FROM UsersEntity U WHERE U.user = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", req.getParameter("destinatary"));
            dbuser = (UsersEntity) query.uniqueResult();
            m.setSender(((SimplePrincipal) req.getSession().getAttribute("org.securityfilter.filter.SecurityRequestWrapper.PRINCIPAL")).getName());
            Set<MessageEntity> messages = dbuser.getMessages();
            m.setUser(dbuser);
            messages.add(m);
            dbuser.setMessages(messages);
            session.save(m);
            session.save(dbuser);
            tx.commit();
        } catch (HibernateException ez) {
            if (tx != null) tx.rollback();
            ez.printStackTrace();
            JOptionPane.showMessageDialog(null, ez);
        } finally {
            session.close();
        }

        resp.sendRedirect("calendar?user=" + ((SimplePrincipal) req.getSession().getAttribute("org.securityfilter.filter.SecurityRequestWrapper.PRINCIPAL")).getName());
    }


}
