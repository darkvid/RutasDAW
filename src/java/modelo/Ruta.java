/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ruta {
    private int id;
    private String nombre;
    private String tipo;
    private Date fecha;
    private String origen;
    private String destino;
    private float valoracion;
    private float distancia;
    private String nombreFichero;

    //constructor por defecto
    public Ruta(){
        id = 0;
        nombre = "Nombre ruta";
        tipo = "Tipo ruta";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
        fecha = date;
        origen = "Origen";
        destino = "Destino";
        valoracion = 0;
        distancia = 0;
        nombreFichero = "";
    }
    
    //constructor sobrecargado
    public Ruta(int i, String n, Date f, String o, String dest, float v, float dis, String nombreF){
        id = i;
        nombre = n;
        fecha = f;
        origen = o;
        destino = dest;
        valoracion = v;
        distancia = dis;
        nombreFichero = nombreF;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public String getNombreFichero() {
        return nombreFichero;
    }

    public void setNombreFichero(String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }
    
}
