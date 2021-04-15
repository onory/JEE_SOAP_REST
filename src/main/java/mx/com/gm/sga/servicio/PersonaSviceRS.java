/*
 * Clase para acceder a web service por medio de REST
 */
package mx.com.gm.sga.servicio;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import mx.com.gm.sga.domain.Persona;

@Path("/personas") // indica que es un Rest Service
@Stateless // indica ser un EJB para inyectar el servicio de persona service
public class PersonaSviceRS {

    @Inject // inyecta el servicio de la clase
    private PersonaService personaService; // variable apara cceder a clase

    //cuanto trabajamos con RS podemos usar metodos HTTP para asociarlos a lso metodos
    //metodo que retorna la lsita de personas
    @GET // se encarga de obtener informacion
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) // indicamos el formato a obtener 

    public List<Persona> listarPersonas() {

        return personaService.listarPersonas();
    }

    //metodo encontrar persona por ID
    @GET // se slecciona por que solo regresa informacion
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) // indicamos el formato a obtener 
    @Path("{id}") // define este subpath como el principal refrenncia = personas/{id}

    public Persona encontrarPersonaPorId(@PathParam("id") int id) {

        return personaService.encontrarPersonaPorId(new Persona(id));
    }

    //metodo agregar persona
    @POST // se usa para adicionar informacion
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) // indicamos el formato a obtener 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) // indicamos el formato q puede cosumir

    public Response agregarPersona(Persona persona) {

        try {
            personaService.registrarPersona(persona);
            return Response.ok().entity(persona).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();

        }
    }

    //metodo modificar persona

    @PUT     // se utiliza para modificar informacion
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) // indicamos el formato a obtener 
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}) // indicamos el formato q puede cosumir
    @Path ("{id}") // recibe dato en este caos id que es el q se modificara
    
                                    //inidca que recibe un parametro
    public Response modificarPersona(@PathParam("id") int id, Persona personaModificada){
       try{ 
        Persona persona = personaService.encontrarPersonaPorId(new Persona (id)); //busca la persona a modificar
        
        if(persona != null){ // valdia si tinee info
            personaService.modificarPersona(personaModificada); //ejecuta metodo de modificar
            return Response.ok().entity(personaModificada).build(); // regresa la respuesta de ok
            
        }else{
            return Response.status(Status.NOT_FOUND).build(); // accion en caso de estar vacio
        }
       }catch(Exception e){
           e.printStackTrace(System.out);
           return Response.status(Status.INTERNAL_SERVER_ERROR).build();
       }
    }  
       
      //metodo eliminar persona
      @DELETE //se usa para eliminar
      @Path("{id}") // inidca que recibira el dato id
                                           //se brinda el dato id
      public Response eliminarPersonaPorId(@PathParam ("id") int id){
       try{ 
        personaService.eliminarPersona( new Persona (id));
        return Response.ok().build();
        
       }catch(Exception e){
           
           e.printStackTrace(System.out);
            return Response.status(Status.NOT_FOUND).build(); //verificar si es el error en lugar  de 404
       }
        
    }
       
        
    
        
        
        
        
        
    }


