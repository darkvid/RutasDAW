
package controladores;

import dao.RutaDAO;
import dao.UsuarioDAO;
import dao.UsuarioDAOJdbc;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import modelo.Usuario;

@Named(value="controladorUsuario")
@ViewScoped
public class ControladorUsuario implements Serializable {
    private static final long serialVersionUID = 1L;

    //Business logic
    @Inject @UsuarioDAOJdbc
    private UsuarioDAO usuarioDAO;
    
    @Inject
    private RutaDAO rutaDAO;
    
    private Part imagen;
    private String fileContent;
    private byte[] bytes_fichero;
    
    private boolean logueado;
    
    //Model
    private Usuario usu;
    private List<Usuario> lu;
    
    public ControladorUsuario(){
        logueado = false;
    }
    
    @PostConstruct
    public void init() {
        //Usefull if DAO injection needed
        usu = new Usuario();
        lu = null;
    }
    
    public void uploadImage() throws IOException {
      
      if(imagen != null){
          System.out.println("hay imagen");
          try{
              if(imagen.getContentType().contains("image/jpeg")){
                  
                String workingDir = "/home/juanpepe/Escritorio/rutas/web/resources/profiles";
                File archivo = new File(workingDir+"/"+usu.getId()+".jpg");
                
                InputStream is = imagen.getInputStream();
                Image image = ImageIO.read(is);
                
                BufferedImage bi = (BufferedImage) image;
                File outputfile = new File("saved.png");
                ImageIO.write(bi, "jpg", archivo);
                
            }else{
                    String workingDir = "/home/juanpepe/Escritorio/rutas/web/resources/profiles";
                    File archivo = new File(workingDir+"/"+"default.jpg");
                    File archivoDestino = new File(workingDir+"/"+usu.getId()+".jpg");
                    FileOutputStream out;
                    try (FileInputStream in = new FileInputStream(archivo)) {
                        out = new FileOutputStream(archivoDestino);
                        int c;
                        while( (c = in.read() ) != -1)
                            out.write(c);
                    }
                    out.close();
              }
          }catch (IOException ex){
                
            }
          
      }else{
          System.out.println("no hay imagen");
            
      }
      
    
    
  }
    
    //Model access
    public List<Usuario> getUsuarios() {
        if (lu==null)
                lu=usuarioDAO.buscaTodos();
        return lu;
    }
    public void setUsuarios (List<Usuario> lu) {
        this.lu=lu;
    }

    public Usuario getUsu () {
        return usu;
    }
    public void setUsu (Usuario u) {
        this.usu=u;
    }
    
    public String logout(){
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        ((HttpSession) ctx.getSession(false)).invalidate();
        logueado = false;
        return "/index.xhtml?faces-redirect=true";
    }
    
    public void comprobarLogin(){
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        Principal userPrincipal = currentInstance.getExternalContext().getUserPrincipal();
        if(userPrincipal != null){
            logueado = true;
        }else{
            logueado = false;
        }
    }
    
    public void recuperaPorDni(){
        String dni =   FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        Usuario usuarioActual;
        usuarioActual=usuarioDAO.buscaDNI(dni);
        if(usuarioActual != null){
            usu = usuarioActual;
        }
        logueado = true;
        System.out.println("logueado como" +usu.getId());
    }
    
    /** Get client from id param*/
    public void recupera() {
        Usuario usuarioActual;
        usuarioActual=usuarioDAO.buscaId(usu.getId());
        if (usuarioActual!=null) {
             usu=usuarioActual;
        } else {
            usu=new Usuario();
        }
    }

    public String crea() throws IOException {
        usuarioDAO.crea(usu); 
        uploadImage();
        return "/users/index.xhtml?faces-redirect=true";
    }

    public String guarda() throws IOException {
        usuarioDAO.guarda(usu);
        uploadImage();
        return "visualiza?faces-redirect=true&id="+usu.getId();
    }        

    public String borra() {
        rutaDAO.actualizaParaBorrar(usu.getId());
        System.out.println("ola k ase");
        usuarioDAO.borra(usu.getId());
        logueado = false;
        return logout();
    }


    /** Update current row from table to DAO*/    
    public void guarda(Usuario usuario) {
        usuarioDAO.guarda(usuario);
        lu=null;
    } 

    public Part getImagen() {
        return imagen;
    }

    public void setImagen(Part imagen) {
        this.imagen = imagen;
    }

    public boolean isLogueado() {
        return logueado;
    }

    public void setLogueado(boolean logueado) {
        this.logueado = logueado;
    }
    
    
    
}
