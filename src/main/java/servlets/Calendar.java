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
import java.util.HashSet;
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

        try {
            Session session = Main.getSession();
            String hql = "FROM UsersEntity U WHERE U.user = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", ((SimplePrincipal) req.getSession().getAttribute("org.securityfilter.filter.SecurityRequestWrapper.PRINCIPAL")).getName());
            UsersEntity dbuser = (UsersEntity) query.uniqueResult();
            createCalendar(dbuser, req);
        } catch (HibernateException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        resp.sendRedirect("calendar.html");
    }

    private void createCalendar(UsersEntity user, HttpServletRequest req) {
//        String s = this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
//        s = s.split("out")[0] + "web/Secure/calendar/events.json.php";
//        File f = new File(s);
        Gson gson = new Gson();
        Set<EventsEntity> events = user.getEvents();
        Set<EventJSONWrapper> wrappedEvents = new HashSet<>();
        for (EventsEntity event : events) {
            wrappedEvents.add(new EventJSONWrapper(event));
        }
        File f = loadFile();


        System.out.println(f.getAbsolutePath());
        try (PrintWriter pr = new PrintWriter(new BufferedWriter(new FileWriter(f)))) {
            String json = "{\"success\": 1, \"result\": " + gson.toJson(wrappedEvents) + "}";
            json = json.replace("clazz", "class");
            pr.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File loadFile() {
        JOptionPane.showMessageDialog(null, "A continuacion elija el archivo que quiere analizar");
        JFileChooser chooser = new JFileChooser();

        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return new File(chooser.getSelectedFile().getAbsolutePath());
        }
        return null;
    }


}
