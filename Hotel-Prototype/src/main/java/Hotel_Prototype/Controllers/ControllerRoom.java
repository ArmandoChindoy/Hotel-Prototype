package Hotel_Prototype.Controllers;

import Hotel_Prototype.Client;
import Hotel_Prototype.Conection;
import Hotel_Prototype.Main;
import Hotel_Prototype.Room;
import Hotel_Prototype.Structures.List;
import Hotel_Prototype.Structures.ListaD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class ControllerRoom {
    ObservableList<String> quantityRooms = FXCollections.observableArrayList();
    //Lista doble y simple
    ListaD<Room> rooms = new ListaD<Room>();

    List<Client> clients = new List();
    Main main;


    @FXML
    Label lblRoom;
    @FXML
    ChoiceBox cBRooms;
    @FXML
    TextField txtfName;
    @FXML
    TextField txtfEmail;
    @FXML
    TextField txtfPhone;

    public void initialize() throws Exception {
        getRooms();
        getClients();
        for (int i = 0; i < rooms.getSize(); i++) {
            quantityRooms.add(String.valueOf(rooms.get(i).getRoomNumber()));
        }
        cBRooms.setItems(quantityRooms);
        cBRooms.setValue("1");
        cBRooms.setOnAction(event -> {
            lblRoom.setText(String.valueOf(cBRooms.getValue()));
            try {
                seeRoom();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        lblRoom.setText(String.valueOf(cBRooms.getValue()));
        seeRoom();

        txtfName.setEditable(false);
        txtfEmail.setEditable(false);
        txtfPhone.setEditable(false);
    }

    public void getRooms() {
        Conection con = new Conection();
        Connection connection = con.conect();
        String getRoom = "Select * from rooms";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getRoom);
            while (result.next()) {
                Room room = new Room();
                boolean ocupide;
                int auxOcupide = result.getInt(1);
                int roomNumber = result.getInt(2);
                int Cliente = result.getInt(3);
                LocalDate checkIn = result.getDate(4).toLocalDate();
                LocalDate checkOut = result.getDate(5).toLocalDate();
                if (auxOcupide == 1) {
                    ocupide = true;
                } else {
                    ocupide = false;
                }
                room.setOcupide(ocupide);
                room.setRoomNumber(String.valueOf(roomNumber));
                room.setClient(Cliente);
                room.setChek_In(checkIn);
                room.setCheck_Out(checkOut);
                rooms.add(room);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getClients() {
        Conection con = new Conection();
        Connection connection = con.conect();
        String getRoom = "Select * from clients";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getRoom);
            while (result.next()) {
                Client client = new Client();
                int Id = result.getInt(1);
                String Name = result.getString(2);
                String Email = result.getString(3);
                int Phone = result.getInt(4);

                client.setId(Id);
                client.setName(Name);
                client.setE_mail(Email);
                client.setPhone(String.valueOf(Phone));
                clients.add(client);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void seeRoom() throws Exception {
        int room = Integer.parseInt((String) cBRooms.getValue()) - 1;
        if (rooms.get(room).isOcupide() == true) {
            for (int j = 0; j < clients.size(); j++) {
                if (rooms.get(room).getClient() == clients.get(j).getId()) {
                    txtfName.setText(clients.get(j).getName());
                    txtfEmail.setText(clients.get(j).getE_mail());
                    txtfPhone.setText(clients.get(j).getPhone());
                }
            }
        } else {
            txtfName.setText("");
            txtfEmail.setText("");
            txtfPhone.setText("");
            JOptionPane.showMessageDialog(null, "This room is not Ocupated");
        }
    }
}
