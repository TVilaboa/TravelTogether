import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Trav
 * Date: 26/03/14
 * Time: 10:11
 */
public class Register extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String pass = req.getParameter("password");
        String email = req.getParameter("email");
        register(user, pass, email);
        resp.sendRedirect("login.jsp");
    }

    private void register(String user, String pass, String email) {
        //TODO
        JOptionPane.showMessageDialog(null, "Succesfully registered");
    }
}
