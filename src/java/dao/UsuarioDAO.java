package dao;

import modelo.Usuario;
import java.util.List;

public interface UsuarioDAO extends GenericDAO<Usuario,Integer>{
        //Declare here specific methods for EntityDAO
    //public List<Cliente> findByNIF(String nif);
    //..
    public Usuario buscaDNI(String id);
}
