package Hotel_Prototype.Controllers;

import Hotel_Prototype.Conection;
import Hotel_Prototype.Employee;
import Hotel_Prototype.Job;
import Hotel_Prototype.Structures.BST;
import Hotel_Prototype.Structures.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ControllerEmpleados {

    BST<Employee> employeeBST = new BST<Employee>();
    List<Employee> employeeList = new List<>();

    // Primera Pestaña{
    ObservableList<LocalTime> hours = FXCollections.observableArrayList();

    @FXML
    ComboBox horaAConsultar;

    //Elementos de la tabla 1
    @FXML
    TableView<Employee> tableViewAntes;

    @FXML
    TableColumn<Employee,Integer> IdColumn;
    @FXML
    TableColumn<Employee,String> NameColumn;
    @FXML
    TableColumn<Employee,String> EmailColumn;
    @FXML
    TableColumn<Employee,Integer> PhoneColumn;
    @FXML
    TableColumn<Employee,String> LaborColumn;
    @FXML
    TableColumn<Employee,LocalTime> Hora_IngresoColumn;
    @FXML
    TableColumn<Employee,LocalTime> Hora_SalidaColumn;

    //Elementos de la tabla 2
    @FXML
    TableView<Employee> tableViewDespues;

    @FXML
    TableColumn<Employee,Integer> Id2Column;
    @FXML
    TableColumn<Employee,String> Name2Column;
    @FXML
    TableColumn<Employee,String> Email2Column;
    @FXML
    TableColumn<Employee,Integer> Phone2Column;
    @FXML
    TableColumn<Employee,String> Labor2Column;
    @FXML
    TableColumn<Employee,LocalTime> Hora_Ingreso2Column;
    @FXML
    TableColumn<Employee,LocalTime> Hora_Salida2Column;

    //Boton Consultar
    @FXML
    Button buttonConsultar;

    //}

    //Segunda Pestaña{

    ObservableList<String> jobs = FXCollections.observableArrayList();
    List<Job> Jobs = new List();


    @FXML
    TextField textFieldId;
    @FXML
    TextField textFieldName;
    @FXML
    TextField textFieldEmail;
    @FXML
    TextField textFieldPhone;
    @FXML
    ComboBox comboBoxLabor;
    @FXML
    ComboBox comboBoxHora_Ingreso;
    @FXML
    ComboBox comboBoxHora_Salida;
    @FXML
    Button buttonRegistrar;


    //}
    @FXML
    public void initialize(){
        setComboBoxHoraConsultar();
        setComboBoxJobs();
        buttonConsultar.setOnAction(e-> setTablas());
        buttonRegistrar.setOnAction(e->setEmployee());
    }

    public void setComboBoxHoraConsultar(){

        for (int i = 0; i <=23; i++) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
            String hora = i+"";
            if(i<10){
                hora = "0"+i;
            }
            LocalTime localTime =  LocalTime.parse(hora+":00",dateTimeFormatter);
            hours.add(localTime);

        }
        horaAConsultar.setItems(hours);
        horaAConsultar.setValue(null);

        comboBoxHora_Ingreso.setItems(hours);
        comboBoxHora_Ingreso.setValue(null);

        comboBoxHora_Salida.setItems(hours);
        comboBoxHora_Salida.setValue(null);
    }

    public void setComboBoxJobs(){
        getJobs();
        for (int i = 0; i <Jobs.size(); i++) {
            try {
                jobs.add(Jobs.get(i).getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        comboBoxLabor.setItems(jobs);
        comboBoxLabor.setValue(null);
    }

    public void tableViewinitializer(){
        //Table View Antes
        IdColumn.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("id"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("name"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("email"));
        PhoneColumn.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("phone"));
        LaborColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("tipoEmpleado"));
        Hora_IngresoColumn.setCellValueFactory(new PropertyValueFactory<Employee,LocalTime>("hora_Ingreso"));
        Hora_SalidaColumn.setCellValueFactory(new PropertyValueFactory<Employee,LocalTime>("Hora_Salida"));

        //Table View Despues
        Id2Column.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("id"));
        Name2Column.setCellValueFactory(new PropertyValueFactory<Employee,String>("name"));
        Email2Column.setCellValueFactory(new PropertyValueFactory<Employee,String>("email"));
        Phone2Column.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("phone"));
        Labor2Column.setCellValueFactory(new PropertyValueFactory<Employee,String>("tipoEmpleado"));
        Hora_Ingreso2Column.setCellValueFactory(new PropertyValueFactory<Employee,LocalTime>("hora_Ingreso"));
        Hora_Salida2Column.setCellValueFactory(new PropertyValueFactory<Employee,LocalTime>("Hora_Salida"));

    }

    public void setTablas() {

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
            if (!(employeeList.estaVacia())) {
                employeeList = new List<>();
            }
            getEmployees();
            removeAllRows();
            //Inicializador de la tabla
            tableViewinitializer();
        if(!employeeList.estaVacia()) {

            for (int i = 0; i < employeeList.size(); i++) {
                try {
                    LocalTime localTime2 = LocalTime.parse(String.valueOf(horaAConsultar.getValue()), dateTimeFormatter);
                    int diferencia = employeeList.get(i).getHora_Ingreso().compareTo(localTime2);
                    if (diferencia < 0) {
                        tableViewAntes.getItems().add(employeeList.get(i));
                    } else {
                        tableViewDespues.getItems().add(employeeList.get(i));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //
            if (!(employeeBST.isEmpty())) {
                employeeBST = new BST<>();
            }

            int l = -1;
            int r = 1;

            LocalTime localTime = LocalTime.parse(horaAConsultar.getValue() + "", dateTimeFormatter);
            employeeBST.insert(0, new Employee(0, null, 0, null, null, localTime, null));

            for (int i = 0; i < employeeList.size(); i++) {
                int diferencia = 0;
                try {
                    diferencia = localTime.compareTo(employeeList.get(i).getHora_Ingreso());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (diferencia > 0) {
                    try {
                        employeeBST.insert(l, employeeList.get(i));
                        l--;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        employeeBST.insert(r, employeeList.get(i));
                        r++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
            System.out.println("Hora A CONSULTAR: "+ localTime+"\n");
            System.out.println("Antes:");
            if(!(employeeBST.getRoot().left==null)){
                employeeBST.inOrder(employeeBST.getRoot().left);
            }
            System.out.println("\n");
            System.out.println("Despues:");
            if(!(employeeBST.getRoot().right==null)){
                employeeBST.inOrder((employeeBST.getRoot().right));
            }
        }else {
            JOptionPane.showMessageDialog(null,"Ingresa datos primero");
        }

    }


    public void removeAllRows(){
        for ( int i = 0; i<tableViewAntes.getItems().size(); i++) {
            tableViewAntes.getItems().clear();
        }
        for ( int i = 0; i<tableViewDespues.getItems().size(); i++) {
            tableViewDespues.getItems().clear();
        }
    }

    public  void getEmployees(){
        Conection con = new Conection();
        Connection connection = con.conect();
        String getEmployees="Select * from empleados";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getEmployees);
            while (result.next()){
                Employee employee = new Employee();

                int auxId = result.getInt(1);
                String auxName = result.getString(2);
                String auxEmail = result.getString(3);
                int auxPhone = result.getInt(4);
                int tipoEmpleado = result.getInt(5);
                String auxtipoEmpleado = "";
                for (int i = 0; i <Jobs.size(); i++) {
                    if(Jobs.get(i).getId()==tipoEmpleado){
                        auxtipoEmpleado=Jobs.get(i).getName();
                    }
                }
                LocalTime hora_Ingreso = result.getTime(6).toLocalTime();
                LocalTime hora_Salida = result.getTime(7).toLocalTime();


                employee.setId(auxId);
                employee.setName(auxName);
                employee.setEmail(auxEmail);
                employee.setPhone(auxPhone);
                employee.setTipoEmpleado(auxtipoEmpleado);
                employee.setHora_Ingreso(hora_Ingreso);
                employee.setHora_Salida(hora_Salida);

                employeeBST.insert(0,employee);
                employeeList.add(employee);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void getJobs(){
        Conection con = new Conection();
        Connection connection = con.conect();
        String getEmployees="Select * from tipoempleados";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getEmployees);
            while (result.next()){
                Job job = new Job();

                int auxId = result.getInt(1);
                String auxName = result.getString(2);

                job.setId(auxId);
                job.setName(auxName);

                Jobs.add(job);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setEmployee(){

                    Conection con = new Conection();
                    Connection connection = con.conect();

                    try {
                        int id = Integer.parseInt(textFieldId.getText());
                        String name = textFieldName.getText();
                        String email = textFieldEmail.getText();
                        String auxphone = textFieldPhone.getText();
                        auxphone = auxphone.replace(" ", "");
                        int phone = 0;
                        if (auxphone.length() != 0) {
                            phone = Integer.parseInt(auxphone);
                        } else {
                            JOptionPane.showMessageDialog(null, "Ingresa un numero de telefono");
                        }
                        String aux_labor = (String.valueOf(comboBoxLabor.getValue()));
                        int labor=0;
                        for (int i = 0; i <Jobs.size() ; i++) {
                            if(aux_labor.equalsIgnoreCase(Jobs.get(i).getName())){
                                labor = Jobs.get(i).getId();
                            }
                        }
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;

                        LocalTime hora_Ingreso = LocalTime.parse(String.valueOf(comboBoxHora_Ingreso.getValue()),dateTimeFormatter);
                        LocalTime hora_Salida = LocalTime.parse(String.valueOf(comboBoxHora_Salida.getValue()),dateTimeFormatter);



                        if (id == 0 || name == null || email == null || phone == 0 || aux_labor == null || hora_Ingreso == null || hora_Salida == null) {
                            JOptionPane.showMessageDialog(null, "Llena todos los campos para continuar", "Input", 3);
                        }

                        //Inserta Empleado
                        String insertTableSQL = "INSERT INTO empleados"
                                + "(id, name, email, telefono,tipoEmpleado,hora_Ingreso,hora_Salida) VALUES"
                                + "(?,?,?,?,?,?,?)";

                        PreparedStatement statement = connection.prepareStatement(insertTableSQL);

                        statement.setInt(1, id);
                        statement.setString(2, name);
                        statement.setString(3, email);
                        statement.setInt(4, phone);
                        statement.setInt(5, labor);
                        statement.setTime(6, Time.valueOf(hora_Ingreso));
                        statement.setTime(7, Time.valueOf(hora_Salida));

                        statement.executeUpdate();
                        connection.close();

                        JOptionPane.showMessageDialog(null, "Registro agregado");
                    } catch (Exception ex) {
                        System.out.println("No esta concetando");
                        ex.printStackTrace();
                    }
                    
                    limpiarFormulario();
    }
    public void limpiarFormulario(){
        textFieldId.setText("");
        textFieldName.setText("");
        textFieldEmail.setText("");
        textFieldPhone.setText("");
        comboBoxLabor.setValue(null);
        comboBoxHora_Ingreso.setValue(null);
        comboBoxHora_Salida.setValue(null);
    }
}
