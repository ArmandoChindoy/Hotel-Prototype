package Hotel_Prototype;

import java.time.LocalDate;

public class Registro {
    int numeroDeRegistro;
    int Id;
    String Name;
    String Email;
    int Phone;
    LocalDate CheckIn;
    LocalDate CheckOut;
    int Room;

    public Registro(int numeroDeRegistro,int id, String name, String email, int phone, LocalDate checkIn, LocalDate checkOut, int room) {
        this.numeroDeRegistro = numeroDeRegistro;
        Name = name;
        Id = id;
        Email = email;
        Phone = phone;
        CheckIn = checkIn;
        CheckOut = checkOut;
        Room = room;
    }
    public Registro() {
        this.numeroDeRegistro = 0;
        Name = null;
        Email = null;
        Phone = 0;
        CheckIn = null;
        CheckOut = null;
        Room = 0;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getNumeroDeRegistro() {
        return numeroDeRegistro;
    }

    public void setNumeroDeRegistro(int numeroDeRegistro) {
        this.numeroDeRegistro = numeroDeRegistro;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    public LocalDate getCheckIn() {
        return CheckIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        CheckIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return CheckOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        CheckOut = checkOut;
    }

    public int getRoom() {
        return Room;
    }

    public void setRoom(int room) {
        Room = room;
    }

    @Override
    public String toString() {
        return "Numero De Registro: " + numeroDeRegistro +
                "\nName: " + Name  +
                "\nEmail: " + Email  +
                "\nPhone: " + Phone +
                "\nCheckIn: " + CheckIn +
                "\nCheckOut: " + CheckOut +
                "\nRoom: " + Room;
    }
}
