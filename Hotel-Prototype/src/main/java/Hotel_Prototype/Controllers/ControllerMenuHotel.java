package Hotel_Prototype.Controllers;

import Hotel_Prototype.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class ControllerMenuHotel {

    Main main;

    @FXML
    Button Check_In_Out;
    @FXML
    Button Rooms;
    @FXML
    Button Admin;
    @FXML
    Button Go_Out;
    @FXML
    Button Registers;
    @FXML
    public void initialize() throws  Exception{
    }

    public void showCheck_In_Out()throws Exception{
        main.showCheck_In_Out();
    }
    @FXML
    public void showRooms()throws Exception{
        main.showRooms();
    }
    @FXML
    public void showAdmin()throws Exception{
        main.showAdmin();
    }
    @FXML
    public void showRegisters()throws Exception{
        main.showRegisters();
    }
    public void getOut(){
        Go_Out.setOnAction(e->System.exit(1));
    }
}
