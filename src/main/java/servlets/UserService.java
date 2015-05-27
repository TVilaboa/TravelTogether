package servlets;

import com.google.gson.Gson;
import hibernate.Main;
import hibernate.UsersEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: TravelTogether
 * Date: 07/02/2015
 * Time: 09:03 PM
 */

@Stateless
@LocalBean
@Path("/users")
public class UserService extends HttpServlet {



    public List<String> findUserNames() {
        List<String> userNames= new ArrayList<String>();
        List<UsersEntity> users= new ArrayList<>();
        try {
            Session session = Main.getSession();
            String hql = "FROM UsersEntity U ";
            Query query = session.createQuery(hql);



           users=query.list();

        } catch (HibernateException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        for(UsersEntity user : users){
            userNames.add(user.getUser());
        }
        return userNames;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param=req.getParameter("query");
        List<String> names= findUserNames().stream().filter(name -> name.contains(param)).collect(Collectors.toList());
        Gson gson=new Gson();

        try(PrintWriter out = resp.getWriter()){
            out.write(gson.toJson(names));
        }
    }
}
