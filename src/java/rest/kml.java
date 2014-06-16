package rest;


import dao.RutaDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import modelo.GeoPoint;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/kml")
public class kml {
    
    @Autowired
    RutaDAO daoRuta;
    
    public kml(){
        
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<GeoPoint> getPuntos(@PathVariable int id, ModelMap model) {
        List<GeoPoint> lista = daoRuta.getPuntos(id);
        return lista;
    }

    
}