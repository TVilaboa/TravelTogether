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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/Secure/calendar/calendar.jsp?userCalendar=" + username + "&userSession=" + username);

        String matchs = "";
        for (String s : getMatchingUsers(dbuser.getId()))
            matchs += ("<tr><td>" + s + "</td></tr>");
        req.setAttribute("matchingUsers", matchs);
        req.setAttribute("userCalendar", dbuser.getUser());
        req.setAttribute("userSession", username);
        rd.forward(req, resp);


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

        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher("/Secure/calendar/calendar.jsp?userCalendar=" + dbuser.getUser() + "&userSession=" + username);

        String matchs = "";
        for (String s : getMatchingUsers(dbuser.getId()))
            matchs += ("<tr><td>" + s + "</td></tr>");
        req.setAttribute("matchingUsers", matchs);
        req.setAttribute("userCalendar", dbuser.getUser());
        req.setAttribute("userSession", username);
        rd.forward(req, resp);

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

    private List<String> getMatchingUsers(int id) {


        List<String> users = new ArrayList<>();

        try {
            Session hibernateSession = Main.getSession();
            String hql = "FROM UsersEntity U WHERE U.id = :userid";
            Query query = hibernateSession.createQuery(hql);
            query.setParameter("userid", id);
            Set<EventsEntity> userEvents = ((UsersEntity) query.uniqueResult()).getEvents();
            hql = "FROM UsersEntity U";
            query = hibernateSession.createQuery(hql);
            List<UsersEntity> allUsers = (List<UsersEntity>) query.list();
            for (UsersEntity user : allUsers) {
                if (user.getId() != id) {
                    for (EventsEntity event : user.getEvents()) {
                        for (EventsEntity userEvent : userEvents) {
                            if (userEvent.getStart() - event.getStart() < 86400000 &&
                                    userEvent.getStart() - event.getStart() > -86400000) {

                                if (userEvent.getTitle().split("\\.")[0].equals(event.getTitle().split("\\.")[0])) {
                                    users.add("<a href=\"#myMessageModal\" data-id=\"" + user.getUser() + "\" class=\"open-sendMessageModal\" data-toggle=\"modal\"  >" + user.getUser() + "</a>" + " in " + event.getTitle().split("\\.")[0]);
                                }


                            }

                        }
                    }
                }
            }

        } catch (HibernateException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return users;

    }

}
