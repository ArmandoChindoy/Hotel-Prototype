package Hotel_Prototype;

import java.time.LocalDate;

public class Room {
    boolean ocupide;
    String roomNumber;
    int client;
    LocalDate chek_In;
    LocalDate check_Out;

    public Room(boolean ocupide, String roomNumber, int client, LocalDate chek_In, LocalDate check_Out) {
        this.ocupide = ocupide;
        this.roomNumber = roomNumber;
        this.client = client;
        this.chek_In = chek_In;
        this.check_Out = check_Out;
    }
    public Room() {
        this.ocupide = false;
        this.roomNumber = null;
        this.client = 0;
        this.chek_In = null;
        this.check_Out = null;
    }

    public boolean isOcupide() {
        return ocupide;
    }

    public void setOcupide(boolean ocupide) {
        this.ocupide = ocupide;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public LocalDate getChek_In() {
        return chek_In;
    }

    public void setChek_In(LocalDate chek_In) {
        this.chek_In = chek_In;
    }

    public LocalDate getCheck_Out() {
        return check_Out;
    }

    public void setCheck_Out(LocalDate check_Out) {
        this.check_Out = check_Out;
    }

    @Override
    public String toString() {
        return "ocupide: " + ocupide +
                "\nroomNumber: " + roomNumber +
                "\nclient: " + client +
                "\nchek_In: " + chek_In +
                "\ncheck_Out: " + check_Out;
    }
}
