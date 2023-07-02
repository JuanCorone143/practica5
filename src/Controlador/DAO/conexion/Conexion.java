
package Controlador.DAO.Conexion;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

/**
 *
 * @author Juan_fer
 */
public class Conexion {
    private XStream xstream;
    public static String URL = "data/";
    
    public Conexion(){
        conectar();
    }
    
    /**
     * Establece la configuración de conexión de XStream.
     *
     * @return La instancia de XStream configurada.
     */
    public XStream conectar(){
        // Crear una instancia de XStream con el driver JettisonMappedXmlDriver
        xstream = new XStream(new JettisonMappedXmlDriver());

        // Configurar el modo de XStream para no hacer referencias a objetos
        xstream.setMode(XStream.NO_REFERENCES);

        // Agregar permiso para cualquier tipo de objeto
        xstream.addPermission(AnyTypePermission.ANY);

        return xstream;
    } 
    
    /**
     * Obtener la instancia de XStream utilizada para la conexión.
     * Si no existe una instancia, se crea y se configura.
     *
     * @return La instancia de XStream.
     */
    public XStream getXstream(){
        if (xstream == null)
            conectar();
        return xstream;
    }
}
