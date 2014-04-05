package servlets;




import hibernate.Main;
import hibernate.UsersEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Trav
 * Date: 26/03/14
 * Time: 10:11
 */
public class Register extends HttpServlet {

    Connection con;



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
        try {
            con= DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        String user = req.getParameter("User");
        String pass = req.getParameter("Password");
        String email = req.getParameter("Email");
        register(user, pass, email,resp);
        //resp.sendRedirect("login.jsp");
    }

    private void register(String user, String pass, String email,HttpServletResponse resp) throws IOException {
        //TODO       //agregar comprobracion por null en  cada dato y cartel de q falta el dato para cada uno
//        resp.setContentType("text/html");
//        PrintWriter out=resp.getWriter();
//        try {
//            PreparedStatement pst=con.prepareStatement("insert into contacts values(?,?,?)");
//            pst.clearParameters();
//            pst.setString(1, user);
//            pst.setString(2, email);
//            pst.setString(3, pass);
//            int i=pst.executeUpdate();
//            out.write(i+" records inserted, <a href='ViewRecords'>View Records</a>");
//
//        } catch (SQLException e) {
//            e.printStackTrace(System.out);
//        }
        Session session = Main.getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
           UsersEntity userE=new UsersEntity();
            userE.setUser(user);
            userE.setEmail(email);
            userE.setPass(pass);
            session.save(userE);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
//        Connection myConnection;
//        DatabaseConnection db=new DatabaseConnection();
//        db.establishConnection("jdbc:mysql://localhost/mydb","root","qweasdrtyfgh22");
//        myConnection=db.getConnection();
//        Statement statement= null;
//        try {
//            statement = myConnection.createStatement();
//
//            statement.execute("insert into Users values('"+6+"','"+user+"','"+pass+"','"+email+"')");
//
//            JOptionPane.showMessageDialog(null, "Datos ingresados correctamente");
//
//            statement.close();
//            myConnection.close();
//            JOptionPane.showMessageDialog(null, "Succesfully registered");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, e + e.getMessage());
//        }


    }
}
