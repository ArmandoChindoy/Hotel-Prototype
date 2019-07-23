package Hotel_Prototype.Controllers;

import Hotel_Prototype.Client;
import Hotel_Prototype.Conection;
import Hotel_Prototype.Main;
import Hotel_Prototype.Room;
import Hotel_Prototype.Structures.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ControllerCheckInOut {
    ObservableList<String> quantityRooms = FXCollections.observableArrayList();
    List<Room> rooms = new List<>();
    List<Client> clients = new List<>();

    //Objeto de la calse main
    Main main;


    @FXML
    Button btnRegister;

    //txtField
    @FXML
    TextField txtCc;
    @FXML
    TextField txtNombre;
    @FXML
    TextField txtE_mail;
    @FXML
    TextField txtPhone;


    //DatePicker
    @FXML
    DatePicker dtpCheck_In;
    @FXML
    DatePicker dtpCheck_Out;

    //Local Dates
    LocalDate ldCheck_In=null;
    LocalDate ldCheck_Out=null;

    //Label
    @FXML
    Label lblTravelDays;

    @FXML
    ChoiceBox choiceBoxQuantityRooms;


    //List of object for the combo box
    @FXML
    public  void  main()throws Exception{
        main.showHotel();
    }
    @FXML
    public void initialize() throws Exception {
        getRooms();
        getClients();

        for (int i = 0; i <rooms.size(); i++) {
            quantityRooms.add(String.valueOf(rooms.get(i).getRoomNumber()));
        }
        choiceBoxQuantityRooms.setItems(quantityRooms);
        choiceBoxQuantityRooms.setValue("1");

        dtpCheck_In.setOnAction(event -> getTravelDays());
        dtpCheck_Out.setOnAction(event -> getTravelDays());
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
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void getClients(){
        Conection con = new Conection();
        Connection connection = con.conect();
        String getRoom="Select * from clients";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getRoom);
            while (result.next()){
                Client client = new Client();
                int Id =  result.getInt(1);
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
    @FXML
    public void register(){
        btnRegister.setOnMouseClicked(e->{
            int roomOcuped = Integer.parseInt(String.valueOf(choiceBoxQuantityRooms.getValue()))-1;
            try {
                if(!(rooms.get(roomOcuped).isOcupide())) {

                    Conection con = new Conection();
                    Connection connection = con.conect();

                    try {
                        String id = txtCc.getText();
                        String nombre = txtNombre.getText();
                        String e_mail = txtE_mail.getText();
                        String auxphone = txtPhone.getText();
                        auxphone = auxphone.replace(" ", "");
                        int phone = 0;
                        if (auxphone.length() != 0) {
                            phone = Integer.parseInt(auxphone);
                        } else {
                            JOptionPane.showMessageDialog(null, "Ingresa un numero de telefono");
                        }
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
                        String Check_in = ldCheck_In.format(dateTimeFormatter);
                        System.out.println(Check_in);
                        String Check_out = ldCheck_Out.format(dateTimeFormatter);
                        System.out.println(Check_out);
                        int room = Integer.parseInt(String.valueOf(choiceBoxQuantityRooms.getValue()));


                        if (id == null || nombre == null || e_mail == null || phone == 0 || ldCheck_In == null || ldCheck_Out == null) {
                            JOptionPane.showMessageDialog(null, "Llena todos los campos para continuar", "Input", 3);
                        }

                        //Inserta regisrto



                        String insertTableSQL = "INSERT INTO registro"
                                + "(Id, Name, Email, Phone,CheckIn,CheckOut,Room) VALUES"
                                + "(?,?,?,?,?,?,?)";

                        PreparedStatement statement = connection.prepareStatement(insertTableSQL);

                        statement.setString(1, id);
                        statement.setString(2, nombre);
                        statement.setString(3, e_mail);
                        statement.setInt(4, phone);
                        statement.setDate(5, Date.valueOf(ldCheck_In));
                        statement.setDate(6, Date.valueOf(ldCheck_Out));
                        statement.setInt(7, room);

                        statement.executeUpdate();


                        //Insertar Cliente

                        String insertClient1 = "INSERT INTO registro(Id, Name, Email, Phone, CheckIn, CheckOut, Room) values(?,?,?,?,?,?,?)";

                        String insertClient = "INSERT INTO clients"
                                + "(Id, Name, Email, Phone) VALUES"
                                + "(?,?,?,?)";

                        PreparedStatement statementClient = connection.prepareStatement(insertClient);

                        statementClient.setString(1, id);
                        statementClient.setString(2, nombre);
                        statementClient.setString(3, e_mail);
                        statementClient.setInt(4, phone);

                        statementClient.executeUpdate();


                        //Insertar Cliente

                        String UpdateRoom1 = "UPDATE rooms SET Ocupide = ? ,Cliente = ? , CheckIn = ? , CheckOut = ?  WHERE  RoomNumber = "+room+";";

                        String udateRoom = "INSERT INTO clients"
                                + "(Id, Name, Email, Phone) VALUES"
                                + "(?,?,?,?)";

                        PreparedStatement statementRoom = connection.prepareStatement(UpdateRoom1);

                        statementRoom.setBoolean(1, true);
                        statementRoom.setString(2, id);
                        statementRoom.setDate(3, Date.valueOf(ldCheck_In));
                        statementRoom.setDate(4, Date.valueOf(ldCheck_Out));

                        statementRoom.executeUpdate();
                        connection.close();


                        JOptionPane.showMessageDialog(null, "Registro agregado");
                    } catch (Exception ex) {
                        System.out.println("No esta concetando");
                        ex.printStackTrace();
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"No hay habitaciones disponibles");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
    public  void getTravelDays(){
        if (dtpCheck_In.getValue()!=null) {
            ldCheck_In = LocalDate.parse(String.valueOf(dtpCheck_In.getValue()));
        }
        if (dtpCheck_Out.getValue()!=null) {
            ldCheck_Out = LocalDate.parse(String.valueOf(dtpCheck_Out.getValue()));
        }
        int diferencesDays;
        if(ldCheck_In!=null&&ldCheck_Out!=null){
            diferencesDays=ldCheck_Out.compareTo(ldCheck_In);
            if(diferencesDays<0){
                lblTravelDays.setText("");
            }else {
                lblTravelDays.setText(String.valueOf(diferencesDays));
            }
        }
    }
}
