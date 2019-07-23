package Hotel_Prototype.Controllers;

import Hotel_Prototype.Client;
import Hotel_Prototype.Conection;
import Hotel_Prototype.Main;
import Hotel_Prototype.Room;
import Hotel_Prototype.Structures.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class ControllerCheckOut {
    //Items ChoiceBox
    ObservableList<String> quantityRooms = FXCollections.observableArrayList();
    List<Room> rooms = new List();
    List<Client> clients = new List<>();
    double RoomPrice;

    Main main;

    //Buttons
    @FXML
    Button btnCheckOut;

    //ChoiceBox
    @FXML
    ChoiceBox cBRooms;

    //Text fields
    @FXML
    TextField txtfCC;
    @FXML
    TextField txtfClient;
    @FXML
    TextField txtfCheck;

    public void initialize() throws Exception {
        getRooms();
        getClients();
        getRoomPrice();

        for (int i = 0; i < rooms.size(); i++) {
            quantityRooms.add(String.valueOf(rooms.get(i).getRoomNumber()));
        }
        cBRooms.setItems(quantityRooms);

        cBRooms.setOnAction(event -> {
            try {
                seeRoom();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        btnCheckOut.setOnAction(event -> checkOut());

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

    public void checkOut() {
        Conection con = new Conection();
        Connection connection = con.conect();
        try {


            String UpdateRoom1 = "UPDATE rooms SET Ocupide = ? ,Cliente = ? , CheckIn = ? , CheckOut = ?  WHERE  RoomNumber = " + cBRooms.getValue() + ";";

            String udateRoom = "INSERT INTO clients"
                    + "(IdColumn, NameColumn, EmailColumn, PhoneColumn) VALUES"
                    + "(?,?,?,?)";

            PreparedStatement statementRoom = connection.prepareStatement(UpdateRoom1);

            statementRoom.setBoolean(1, false);
            statementRoom.setInt(2, 0);
            statementRoom.setDate(3, Date.valueOf(LocalDate.now()));
            statementRoom.setDate(4, Date.valueOf(LocalDate.now()));

            statementRoom.executeUpdate();
            connection.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No esta conectando");
        }
        try {
            main.showRooms();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void seeRoom() throws Exception {
        int room = Integer.parseInt((String) cBRooms.getValue()) - 1;
        if (rooms.get(room).isOcupide() == true) {
            for (int j = 0; j < clients.size(); j++) {
                if (rooms.get(room).getClient() == clients.get(j).getId()) {
                    txtfCC.setText(String.valueOf(clients.get(j).getId()));
                    txtfClient.setText(clients.get(j).getName());

                    txtfCheck.setText(String.valueOf(getCheck(getTravelDays(rooms.get(room).getChek_In(), rooms.get(room).getCheck_Out()), 0)));

                }
            }
        } else {
            txtfClient.setText("");
            txtfCC.setText("");
            txtfCheck.setText("");
            JOptionPane.showMessageDialog(null, "This room is not Ocupated");
        }
    }

    public int getTravelDays(LocalDate ldCheck_In, LocalDate ldCheck_Out) {

        int diferencesDays = 0;
        if (ldCheck_In != null && ldCheck_Out != null) {
            diferencesDays = ldCheck_Out.compareTo(ldCheck_In);
        }
        return diferencesDays;
    }

    // metodo recursivo
    public double getCheck(int days, double check) {
        if (days == 0) {
            return check;
        } else {
            check = check + RoomPrice;
            return getCheck(days - 1, check);
        }
    }

    public void getRoomPrice() {
        Conection con = new Conection();
        Connection connection = con.conect();
        String getPrice = "Select * from hotelsetting";
        Statement statement;
        String roomPrice = "";
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getPrice);
            while (result.next()) {
                roomPrice = result.getString(1);
            }
            RoomPrice = Double.parseDouble(roomPrice);
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

