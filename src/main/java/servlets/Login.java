package servlets;

import model.user.LoginRealm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        LoginRealm realm = new LoginRealm();
        if (realm.booleanAuthenticate(user, pass)) {
            resp.sendRedirect(resp.encodeRedirectURL("/Secure/welcome.jsp"));
        } else
            resp.sendRedirect("/login.jsp");

    }


}
