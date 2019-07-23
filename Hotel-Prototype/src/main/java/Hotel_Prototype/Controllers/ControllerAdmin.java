package Hotel_Prototype.Controllers;

import Hotel_Prototype.Conection;
import Hotel_Prototype.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerAdmin {
    Main main;
    //Buttons
    @FXML
    Button Go_Back;
    @FXML
    Button btnSave;
    @FXML
    Button btnSave2;
    //TxtAreas
    @FXML
    TextField txtRoomPrice;

    public void initialize(){
        btnSave2.setOnAction(e-> {
            try {
                main.showEmployShedule();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        getRoomPrice();

    }
    @FXML
    public  void save(){
            Conection con = new Conection();
            Connection conection = con.conect();

            double roomPrice = Double.parseDouble(txtRoomPrice.getText());

            String setSave = "UPDATE hotelsetting SET RoomPrice ="+roomPrice+";";

        try {
            Statement statement = conection.createStatement();
            statement.executeUpdate(setSave);
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void goBack() throws IOException {
        main.showPrincipalMenu();
    }

    public void getRoomPrice(){
        Conection con = new Conection();
        Connection connection = con.conect();
        String getPrice="Select * from hotelsetting";
        Statement statement;
        String roomPrice = "";
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getPrice);
            while (result.next()){
                roomPrice = result.getString(1);
            }
            txtRoomPrice.setText(roomPrice);
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
