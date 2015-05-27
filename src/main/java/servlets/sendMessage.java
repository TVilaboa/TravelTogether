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
import java.util.Date;
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
        UsersEntity receiver = null;
        UsersEntity sender = null;
        try {

            MessageEntity m = new MessageEntity();
            m.setDate(new Date());

            m.setText(req.getParameter("Message"));
            System.out.println(req.getParameter("destinatary"));
            tx = session.beginTransaction();
            String hql = "FROM UsersEntity U WHERE U.user = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", req.getParameter("destinatary"));
            receiver = (UsersEntity) query.uniqueResult();
            String senderName = ((SimplePrincipal) req.getSession().getAttribute("org.securityfilter.filter.SecurityRequestWrapper.PRINCIPAL")).getName();
            m.setSender(senderName);
            String hql1 = "FROM UsersEntity U WHERE U.user = :username";
            Query query1 = session.createQuery(hql1);
            query1.setParameter("username",senderName );
            sender = (UsersEntity) query1.uniqueResult();
            m.setSenderEmail(sender.getEmail());
            Set<MessageEntity> messages = receiver.getMessages();
            m.setUser(receiver);
            messages.add(m);
            receiver.setMessages(messages);
            session.save(m);
            session.save(receiver);
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
