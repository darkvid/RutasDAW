/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

public class GeoPoint {

    private String latitud;
    private String longitud;
    
    public GeoPoint(){
        
    }
    
    public GeoPoint(String lat, String lon){
        latitud = lat;
        longitud = lon;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }
    
    
    
}
