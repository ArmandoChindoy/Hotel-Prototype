package Hotel_Prototype;

public class Client {
    int Id;
    String Name;
    String E_mail;
    String Phone;

    public Client(int id, String name, String e_mail, String phone) {
        Id = id;
        Name = name;
        E_mail = e_mail;
        Phone = phone;
    }
    public Client() {
        Id = 0;
        Name = "";
        E_mail = "";
        Phone = "";
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getE_mail() {
        return E_mail;
    }

    public void setE_mail(String e_mail) {
        E_mail = e_mail;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    @Override
    public String toString() {
        return "Name : " + Name  +
                "\nE_mail : " + E_mail +
                "\nPhone : " + Phone ;
    }
}
