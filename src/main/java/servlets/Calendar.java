package servlets;

import com.google.gson.Gson;
import hibernate.EventJSONWrapper;
import hibernate.EventsEntity;
import hibernate.Main;
import hibernate.UsersEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.securityfilter.realm.SimplePrincipal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Trav
 * Date: 23/04/14
 * Time: 10:16
 */
public class Calendar extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersEntity dbuser = null;
        String username = ((SimplePrincipal) req.getSession().getAttribute("org.securityfilter.filter.SecurityRequestWrapper.PRINCIPAL")).getName();
        try {
            Session session = Main.getSession();
            String hql = "FROM UsersEntity U WHERE U.user = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);

            dbuser = (UsersEntity) query.uniqueResult();
            createCalendar(dbuser, req);
        } catch (HibernateException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        req.getSession().setAttribute("matchingUsers", getMatchingUsers(dbuser.getId()));

        resp.sendRedirect("calendar.jsp?userCalendar=" + username + "&userSession=" + username);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersEntity dbuser = null;
        String username = ((SimplePrincipal) req.getSession().getAttribute("org.securityfilter.filter.SecurityRequestWrapper.PRINCIPAL")).getName();
        try {
            Session session = Main.getSession();

            String user = req.getParameter("user");

            String hql = "FROM UsersEntity U WHERE U.user = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", user);
            dbuser = (UsersEntity) query.uniqueResult();
            if (dbuser == null) {
                JOptionPane.showMessageDialog(null, "Incorrect user");
                resp.sendRedirect("/Secure/welcome.jsp");
                return;
            }
            createCalendar(dbuser, req);
        } catch (HibernateException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        req.getSession().setAttribute("matchingUsers", getMatchingUsers(dbuser.getId()));
        resp.sendRedirect("calendar.jsp?userCalendar=" + dbuser.getUser() + "&userSession=" + username);
    }

    private void createCalendar(UsersEntity user, HttpServletRequest req) {
        Gson gson = new Gson();
        Set<EventsEntity> events = user.getEvents();
        Set<EventJSONWrapper> wrappedEvents = new HashSet<>();
        for (EventsEntity event : events) {
            wrappedEvents.add(new EventJSONWrapper(event));
        }
        String s = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
        s = s.split("out")[0] + "\\out\\artifacts\\TravelTogether_war_exploded\\Secure\\calendar\\events.json.php";
        s = s.replace('%', ' ');
        s = s.replace("20", "");


        try (PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(new File(s))))) {
            String json = "{\"success\": 1, \"result\": " + gson.toJson(wrappedEvents) + "}";
            json = json.replace("clazz", "class");
            pr.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File loadFile() {
        JOptionPane.showMessageDialog(null, "A continuacion elija el archivo que se va a utilizar para guardar los eventos");
        JFileChooser chooser = new JFileChooser();

        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return new File(chooser.getSelectedFile().getAbsolutePath());
        }
        return null;
    }

    private List<UsersEntity> getMatchingUsers(int id) {
        //AddEvent.java comments on problems with this aproach
        UsersEntity dbuser = null;
        List<UsersEntity> users = new ArrayList<>();
        try {
            Session hibernateSession = Main.getSession();


            String hql = "FROM UsersEntity U WHERE U.id = :userid";
            Query query = hibernateSession.createQuery(hql);
            query.setParameter("userid", id);


            for (Iterator iterator = query.iterate(); iterator.hasNext(); ) {
                users.add((UsersEntity) iterator.next());
            }


        } catch (HibernateException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return users;

    }

}
