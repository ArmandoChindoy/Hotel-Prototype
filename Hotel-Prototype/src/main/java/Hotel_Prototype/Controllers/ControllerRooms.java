package Hotel_Prototype.Controllers;

import Hotel_Prototype.Conection;
import Hotel_Prototype.Main;
import Hotel_Prototype.Room;
import Hotel_Prototype.Structures.List;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class ControllerRooms {
    // listas cargadas con las consultas de la base de datos
    List<Room> rooms = new List<>();
    List<Button> roomss = new List<>();
    Main main;
    @FXML
    Button Return;
    @FXML
    Button btnAdmin;
    @FXML
    Button btnRegister;
    @FXML
    Button btnMenuHotel;
    @FXML
    Button btnCheckOut;
  //Rooms
    @FXML
    Button btnRoom1;
    @FXML
    Button btnRoom2;
    @FXML
    Button btnRoom3;
    @FXML
    Button btnRoom4;
    @FXML
    Button btnRoom5;
    @FXML
    Button btnRoom6;
    @FXML
    ImageView iVAdmin;
    @FXML
    public void initialize() throws Exception{
        //GET ROOMS INFO FOR THE DB
        getRooms();

        roomss.add(btnRoom1);
        roomss.add(btnRoom2);
        roomss.add(btnRoom3);
        roomss.add(btnRoom4);
        roomss.add(btnRoom5);
        roomss.add(btnRoom6);

        //Put colors to the rooms buttons
        for (int i = 0; i <rooms.size() ; i++) {

                if (rooms.get(i).isOcupide() == true) {
                    roomss.get(i).setBackground(new Background(new BackgroundFill(Color.web("#ff0000"), CornerRadii.EMPTY, Insets.EMPTY)));
                    roomss.get(i).setText(rooms.get(i).getRoomNumber());
                } else {
                    roomss.get(i).setBackground(new Background(new BackgroundFill(Color.web("#008f39"), CornerRadii.EMPTY, Insets.EMPTY)));
                    roomss.get(i).setText(rooms.get(i).getRoomNumber());
                }
        }

        //Actions

        btnCheckOut.setOnAction(e->{
            try {
                main.showCheckOut();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btnRegister.setOnAction(e->{
            try {
                main.showCheck_In_Out();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        btnMenuHotel.setOnAction(e->{
            try {showMenuHotel();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        //Rooms buttons Actions

        for (int i = 0; i <roomss.size() ; i++) {
            roomss.get(i).setOnAction(event -> {
                try {
                    main.showRoom();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }
    public  void getRooms(){
        Conection con = new Conection();
        Connection connection = con.conect();
        String getRoom="Select * from rooms";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getRoom);
            while (result.next()){
                Room room = new Room();
                boolean ocupide;
                int auxOcupide = result.getInt(1);
                int roomNumber = result.getInt(2);
                int Cliente = result.getInt(3);
                LocalDate checkIn = result.getDate(4).toLocalDate();
                LocalDate checkOut = result.getDate(5).toLocalDate();
                if(auxOcupide==1){
                    ocupide=true;
                }
                else {
                    ocupide=false;
                }
                room.setOcupide(ocupide);
                room.setRoomNumber(String.valueOf(roomNumber));
                room.setClient(Cliente);
                room.setChek_In(checkIn);
                room.setCheck_Out(checkOut);
                rooms.add(room);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void showMenuHotel() throws IOException {
        main.showPrincipalMenu();
    }
    @FXML
    public void showCheckOut() throws IOException {
        main.showCheckOut();
    }
    @FXML
    public void showAdmin() throws IOException {
        iVAdmin.setOnMouseClicked(e->{
            try {
                main.showAdmin();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        btnAdmin.setOnAction(e->{
            try {
                main.showAdmin();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }
}
