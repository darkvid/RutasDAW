package modelo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Usuario {
    private int id;
    @Size(min=4,max=25)
    private String nombre;
    @Pattern(regexp="\\d{7,8}(-?[a-zA-Z])?",message="El dato no tiene la estructura correcta 12345678-Y")
    private String dni;
    private String apellidos;
    private String mail;
    @Size(min=4,max=25)
    private String password;
    
    public Usuario(){
        id = 0;
        nombre = "Nombre";
        dni = "XXXXXXXX-X";
        apellidos = "Apellidos";
        mail = "E-mail";
        password = "";
    }
    
    public Usuario(int i, String n, String d, String a, String e){
        id = i;
        nombre = n;
        dni = d;
        apellidos = a;
        mail = e;
        password = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
