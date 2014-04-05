package database;

import javax.swing.*;
import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: TravelTogether
 * Date: 29/03/14
 * Time: 18:25
 */
public class TestBD {

    public static void main(String[] args)
    {
        Connection miConexion;
        DatabaseConnection db=new DatabaseConnection();
        db.establishConnection("jdbc:mysql://localhost/mydb","root","qweasdrtyfgh22");
        miConexion=db.getConnection();

        if(miConexion!=null)
        {

            JOptionPane.showMessageDialog(null, "Conexión Realizada Correctamente");
        }
    }


}
