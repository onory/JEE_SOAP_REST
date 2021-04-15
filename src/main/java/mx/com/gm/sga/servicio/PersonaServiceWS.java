/*
 * interfase que implementara el sga con webservice
 */
package mx.com.gm.sga.servicio;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import mx.com.gm.sga.domain.Persona;

@WebService // indica ser un web service
public interface PersonaServiceWS {
    
    @WebMethod // indnca es un metos para consumo soap
    public List<Persona> listarPersonas();
}
