package Hotel_Prototype;

import java.time.LocalTime;

public class Employee implements Comparable<Employee> {
    int id;
    String name;
    int phone;
    String email;
    String tipoEmpleado;
    LocalTime hora_Ingreso;
    LocalTime hora_Salida;

    public Employee(int id, String name, int phone, String email, String tipoEmpleado, LocalTime hora_Ingreso, LocalTime hora_Salida) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.tipoEmpleado = tipoEmpleado;
        this.hora_Ingreso = hora_Ingreso;
        this.hora_Salida = hora_Salida;
    }

    public Employee() {
        this.id = 0;
        this.name = null;
        this.phone = 0;
        this.email = null;
        this.tipoEmpleado = null;
        this.hora_Ingreso = null;
        this.hora_Salida = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public LocalTime getHora_Ingreso() {
        return hora_Ingreso;
    }

    public void setHora_Ingreso(LocalTime hora_Ingreso) {
        this.hora_Ingreso = hora_Ingreso;
    }

    public LocalTime getHora_Salida() {
        return hora_Salida;
    }

    public void setHora_Salida(LocalTime hora_Salida) {
        this.hora_Salida = hora_Salida;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", tipoEmpleado='" + tipoEmpleado + '\'' +
                ", hora_Ingreso=" + hora_Ingreso +
                ", hora_Salida=" + hora_Salida +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        return 0;
    }
}
