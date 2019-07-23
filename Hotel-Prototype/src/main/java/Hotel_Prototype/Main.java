package Hotel_Prototype;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    private static Stage primaryStage;
    private static BorderPane mainLayout;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage=primaryStage;
        this.primaryStage.setTitle("Hotel");

        showHotel();

    }

    public  void showHotel() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/FXML/menuHotel.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene menuHotel = new Scene(root);
        primaryStage.setScene(menuHotel);
        primaryStage.show();
    }
    public  static void showCheck_In_Out() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/FXML/Check_In_Out.fxml"));
        BorderPane CheckLayout = loader.load();

        Stage stageCheck_In_Out = new Stage();
        stageCheck_In_Out.setTitle("Check_In_Out");
        stageCheck_In_Out.initModality(Modality.WINDOW_MODAL);
        stageCheck_In_Out.initOwner(primaryStage);

        Scene Check_In_Out_Scene = new Scene(CheckLayout);
        stageCheck_In_Out.setScene(Check_In_Out_Scene);
        stageCheck_In_Out.showAndWait();
    }
    public  static void showRooms() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/FXML/Rooms.fxml"));
        BorderPane RoomsLayout = loader.load();

        primaryStage.setTitle("Rooms");

        Scene Rooms_Scene = new Scene(RoomsLayout);
        primaryStage.setScene(Rooms_Scene);
        primaryStage.show();
        //

    }
    public  static void showPrincipalMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/FXML/MenuHotel.fxml"));
        BorderPane menuHotelLayout = loader.load();

        primaryStage.setTitle("Hotel");

        Scene Rooms_Scene = new Scene(menuHotelLayout);
        primaryStage.setScene(Rooms_Scene);
        primaryStage.show();
    }
    public  static void showAdmin() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/FXML/Admin.fxml"));
        BorderPane AdminLayout = loader.load();
        Stage stageAdmin = new Stage();

        stageAdmin.initModality(Modality.WINDOW_MODAL);
        stageAdmin.initOwner(primaryStage);

        Scene AdminScene = new Scene(AdminLayout);
        stageAdmin.setScene(AdminScene);
        stageAdmin.showAndWait();

        stageAdmin.setTitle("Administrador");


    }
    public  static void showCheckOut() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/FXML/CheckOut.fxml"));
        BorderPane checkOutLayout = loader.load();
        Stage stageCheckOut = new Stage();

        stageCheckOut.initModality(Modality.WINDOW_MODAL);
        stageCheckOut.initOwner(primaryStage);

        Scene CheckOutScene = new Scene(checkOutLayout);
        stageCheckOut.setScene(CheckOutScene);
        stageCheckOut.showAndWait();

        stageCheckOut.setTitle("Check-Out");


    }
    public  static void showRoom() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/FXML/Room.fxml"));
        BorderPane roomLayout = loader.load();
        Stage stageRoom = new Stage();

        stageRoom.initModality(Modality.WINDOW_MODAL);
        stageRoom.initOwner(primaryStage);

        Scene RoomScene = new Scene(roomLayout);
        stageRoom.setScene(RoomScene);
        stageRoom.showAndWait();

        stageRoom.setTitle("Administrador");


    }
    public  static void showRegisters() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/FXML/Registers.fxml"));
        BorderPane roomLayout = loader.load();
        Stage stageRoom = new Stage();

        stageRoom.initModality(Modality.WINDOW_MODAL);
        stageRoom.initOwner(primaryStage);

        Scene RoomScene = new Scene(roomLayout);
        stageRoom.setScene(RoomScene);
        stageRoom.showAndWait();

        stageRoom.setTitle("Administrador");
    }

    public  static void showEmployShedule() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/FXML/Empleados.fxml"));
        BorderPane roomLayout = loader.load();
        Stage stageRoom = new Stage();

        stageRoom.initModality(Modality.WINDOW_MODAL);
        stageRoom.initOwner(primaryStage);

        Scene RoomScene = new Scene(roomLayout);
        stageRoom.setScene(RoomScene);
        stageRoom.showAndWait();

        stageRoom.setTitle("Administrador");
    }
    public static void main(String[] args) {
        launch(args);
    }
}
