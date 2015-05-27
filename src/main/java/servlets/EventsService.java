package servlets;

import com.google.gson.Gson;
import hibernate.EventJSONWrapper;
import hibernate.EventsEntity;
import hibernate.Main;
import hibernate.UsersEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: TravelTogether
 * Date: 19/02/2015
 * Time: 09:12 PM
 */
@Stateless
@LocalBean
@Path("/users")
public class EventsService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int param=Integer.parseInt(req.getParameter("id"));
        Session session = Main.getSession();
        String hql = "FROM EventsEntity E WHERE E.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", param);
        EventsEntity event = (EventsEntity) query.uniqueResult();
        Gson gson=new Gson();

        try(PrintWriter out = resp.getWriter()){
            out.write(gson.toJson(new EventJSONWrapper(event)));
        }
    }
}
