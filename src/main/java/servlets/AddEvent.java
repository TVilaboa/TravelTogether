package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: TravelTogether
 * Date: 02/05/2014
 * Time: 07:50 PM
 */
public class AddEvent extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("Title"));
        System.out.println(req.getParameter("URL"));
        System.out.println(req.getParameter("From"));
        System.out.println(req.getParameter("To"));
        System.out.println(req.getParameter("StartDay"));
        System.out.println(req.getParameter("StartHour"));
        System.out.println(req.getParameter("EndDay"));
        System.out.println(req.getParameter("EndHour"));
        System.out.println(req.getParameter("optionsRadios"));
        resp.sendRedirect("calendar");
    }
}
