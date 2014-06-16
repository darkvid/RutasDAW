
package controladores;
  
import dao.RutaDAO;
import dao.UsuarioDAO;
import dao.UsuarioDAOJdbc;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import modelo.GeoPoint;
import modelo.Ruta;
import modelo.Usuario;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

@Named(value="controladorRuta")
@RequestScoped
public class ControladorRuta implements Serializable{  
  
    //DAO
    @Inject
    private RutaDAO rutaDAO;
    
    @Inject @UsuarioDAOJdbc
    private UsuarioDAO usuDAO;
    
    //Model
    private Ruta rut;
    private Usuario usu;
    private List<Ruta> populares;
    
    private List<Usuario> participantes;
    
    private Part file;
    private String texto;
    private String fileContent;
    private byte[] bytes_fichero;
 
    String tipo;
    int valoracion;
    
    public ControladorRuta(){
        tipo="default";
    }
    
    public Ruta getRut(){
        return rut;
    }
    
    public int getValoracion(){
        return valoracion;
    }
    
    public void setValoracion(int v){
        valoracion = v;
    }
    
    @PostConstruct
    public void init() {
        rut = new Ruta();
        populares = rutaDAO.getPopulares();
    }
    
    public void recuperaPorTipo(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String p = (String) facesContext.getExternalContext().
                getRequestParameterMap().get("tipo");
    }
    
    /** Selecciona ruta por id*/
    public void recupera() {
        Ruta ruta;
        ruta=rutaDAO.buscaId(rut.getId());
        if (ruta!=null) {
             rut=ruta;
             valoracion = (int) rut.getValoracion();
        } else {
            rut=new Ruta();
        }
        //recupero la lista de participantes en esa ruta
        List<Integer> idParticipantes = rutaDAO.getIdParticipantes(rut.getId());
        participantes = new ArrayList<Usuario>();
        for(Integer i: idParticipantes){
            Usuario aux = usuDAO.buscaId(i);
            participantes.add(aux);
        }
    }
    
    public String crea() {
        String dni =   FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        Usuario usuarioActual;
        usu=usuarioActual=usuDAO.buscaDNI(dni);
        rutaDAO.crea(rut, usuarioActual.getId()); 
        upload();
        //Post-Redirect-Get
        return "/rutas/visualiza?faces-redirect=true&id="+rut.getId();
    }
    
    public String unirse(){
        String dni = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        System.out.println(dni);
        Usuario usuarioActual;
        usu=usuarioActual=usuDAO.buscaDNI(dni);
        rutaDAO.unirseRuta(rut.getId(), usu.getId());
        return "visualiza?faces-redirect=true&id="+rut.getId();
    }
    
    public void valorar(){
        //Está cogiendo la misma valoración
        rutaDAO.actualizaValoracion(rut.getId(),valoracion, rut.getValoracion());
    }
    
    public void upload() {
      
      if(file != null){
          try{
              
              InputStream is = file.getInputStream();
              fileContent = new Scanner(is).useDelimiter("\\A").next();
              texto = fileContent;
              
              String[] textos = texto.split("coordinates");
              //voy a leer el fichero para extraer las coordenadas
              String conjuntoCoordenadas = textos[textos.length-2];
              //quito los < y />
              conjuntoCoordenadas = conjuntoCoordenadas.replace("<", "").replace(">", "").replace("/", "");
              //ahora separo las coordenadas
              String[] coordenadas = conjuntoCoordenadas.split(" ");
              //creo la lista de puntos
              List<GeoPoint> latLng = new ArrayList<GeoPoint>();
              for (int i = 1; i<coordenadas.length-1;i++) {
                  String[] coor = coordenadas[i].split(",");
                  //hacer comparación de si coor esta vacio
                  latLng.add(new GeoPoint(coor[1],coor[0]));
              }
              
              //ahora los guardo a la BBDD
              rutaDAO.guardaPuntos(latLng, rut.getId());
              
              
              //creo el fichero
              String prefix = FilenameUtils.getBaseName(file.getName());
              String suffix = FilenameUtils.getExtension(file.getName());
              //File archivo = File.createTempFile(prefix+"-", "."+suffix);
              String workingDir = "/home/juanpepe/Escritorio/rutas/web/resources/kml";
              //File archivo = new File(workingDir+"/prueba.kml");
              File archivo = new File(workingDir+"/"+rut.getId()+".kml");
              texto = archivo.getAbsolutePath();
              
              OutputStream output = new FileOutputStream(archivo);
              bytes_fichero = fileContent.getBytes(Charset.forName("UTF-8"));
              try{
                  output.write(bytes_fichero);
                  IOUtils.copy(is,output);
              }finally{
                  IOUtils.closeQuietly(output);
                  IOUtils.closeQuietly(is);
              }
          }catch (IOException ex){
              
          }
      }
      
    
    
  }

    public List<Ruta> getPopulares() {
        return populares;
    }

    public void setPopulares(List<Ruta> populares) {
        this.populares = populares;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
  
  
  
  public Part getFile() {
    return file;
  }
  
  public void setTexto(String t){
      this.texto = t;
  }
  
  public String getTexto(){
      return texto;
  }
 
  public void setFile(Part file) {
    this.file = file;
  }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }
  
  
}  
  
