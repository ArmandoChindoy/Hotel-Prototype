package Hotel_Prototype;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conection {
    Connection connection;

    public Connection conect(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/green bit hotel?useTimezone=true&serverTimezone=UTC","root","");
            JOptionPane.showMessageDialog(null,"Conectado Correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"No Conectado Correctamente");
            e.printStackTrace();
        }
        return connection;
    }
}
