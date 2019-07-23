package Hotel_Prototype.Controllers;

import Hotel_Prototype.Conection;
import Hotel_Prototype.Registro;
import Hotel_Prototype.Structures.Queue;
import Hotel_Prototype.Structures.Stack;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class ControllerRegistro implements Initializable {
    // Aqui utilizo Queues y satcks carga los datos y dependiendo de la opcion del usuario utilizo Queues para mostrarlo de forma acendente y Stack para decendente
    public Queue<Registro> registros = new Queue<>();
    Stack<Registro> registros2 = new Stack<>();

    @FXML
    TableView<Registro> tVRegistro;
    @FXML
    TableColumn<Registro,Integer> NRegistro;
    @FXML
    TableColumn<Registro,Integer> Id;
    @FXML
    TableColumn<Registro,String> Name;
    @FXML
    TableColumn<Registro,String> Email;
    @FXML
    TableColumn<Registro,Integer> Phone;
    @FXML
    TableColumn<Registro,LocalDate> CheckIn;
    @FXML
    TableColumn<Registro,LocalDate> CheckOut;
    @FXML
    TableColumn<Registro,Integer> Room;

    public ObservableList<Registro> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getRooms();
        int ver = Integer.parseInt(JOptionPane.showInputDialog(null, "Como desea ver la tabla\n 1.decendente 2.Acendente", "Orientacion", 1));
        if (ver == 2) {
            if (!registros.empty()) {
                for (int i = 0; i < registros.size(); i++) {
                    try {
                        observableList.add(getRegistros());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            } else if (ver == 1) {
                if (!registros2.empty()) {
                    for (int i = 0; i < registros2.size(); i++) {
                        try {
                            observableList.add(getRegistros2(i));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            NRegistro.setCellValueFactory(new PropertyValueFactory<Registro, Integer>("numeroDeRegistro"));
            Id.setCellValueFactory(new PropertyValueFactory<Registro, Integer>("Id"));
            Name.setCellValueFactory(new PropertyValueFactory<Registro, String>("Name"));
            Email.setCellValueFactory(new PropertyValueFactory<Registro, String>("Email"));
            Phone.setCellValueFactory(new PropertyValueFactory<Registro, Integer>("Phone"));
            CheckIn.setCellValueFactory(new PropertyValueFactory<Registro, LocalDate>("CheckIn"));
            CheckOut.setCellValueFactory(new PropertyValueFactory<Registro, LocalDate>("CheckOut"));
            Room.setCellValueFactory(new PropertyValueFactory<Registro, Integer>("Room"));
            tVRegistro.setItems(observableList);
    }
    public  void getRooms(){
        Conection con = new Conection();
        Connection connection = con.conect();
        String getRoom="Select * from registro";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getRoom);
            while (result.next()){
                Registro registro = new Registro();

                int auxId = result.getInt(1);
                String auxName = result.getString(2);
                String auxEmail = result.getString(3);
                int auxPhone = result.getInt(4);
                LocalDate auxCheckIn = result.getDate(5).toLocalDate();
                System.out.println(auxCheckIn);
                LocalDate auxCheckOut = result.getDate(6).toLocalDate();
                System.out.println(auxCheckOut);
                int auxRoom = result.getInt(7);

                registro.setNumeroDeRegistro(result.getInt(8));
                registro.setId(auxId);
                registro.setName(auxName);
                registro.setEmail(auxEmail);
                registro.setPhone(auxPhone);
                registro.setCheckIn(auxCheckIn);
                registro.setCheckOut(auxCheckOut);
                registro.setRoom(auxRoom);
                registros.enqueue(registro);
                registros2.push(registro);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  Registro getRegistros() throws Exception{
            Registro registro;
            registro = registros.getdequeue();
            return registro;
    }
    public  Registro getRegistros2(int i) throws Exception{
        Registro registro;
        registro = registros.get(i);
        return registro;
    }


}
