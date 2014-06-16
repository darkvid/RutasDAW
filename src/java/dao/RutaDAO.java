package dao;


import java.util.List;
import modelo.GeoPoint;
import modelo.Ruta;

public interface RutaDAO extends GenericDAO<Ruta,Integer>{
    public boolean guardaPuntos(List<GeoPoint> lista, int identificador);
    public List<GeoPoint> getPuntos(int id);
    public List<Ruta> getPopulares();
    public void actualizaValoracion(int id, int nuevaVal, float viejaVal);
    public boolean crea(Ruta r, int id);
    public boolean actualizaParaBorrar(int id_usuario);
    public List<Integer> getIdParticipantes(int id_ruta);
    public void unirseRuta(int id_ruta, int id_usuario);
}
