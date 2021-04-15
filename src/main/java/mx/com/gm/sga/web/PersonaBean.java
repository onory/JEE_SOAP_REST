package mx.com.gm.sga.web;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.servicio.PersonaService;
import org.apache.logging.log4j.*;
import org.primefaces.event.RowEditEvent;

@Named("personaBean") // indica el nombre oara el bean de JSF
@RequestScoped // se determina el alcance
public class PersonaBean {
    
    Logger log = LogManager.getRootLogger();
    
    @Inject //inyecta el servicio de persona de EJB de PersonaService del packeges servicio
    private PersonaService personaService;

    private Persona personaSeleccionada;
    
    List<Persona> personas; // atributo donde se almacena la personas
   
   //constructor vacio 
    public PersonaBean(){
        log.debug("Iniciando el objeto PersonaBean");
    }
    
    @PostConstruct //indica que cuando se crea el objeto se incialice por medio del metodoinicializar()
    public void inicializar(){
        //Inciamos las variables
        this.personas = personaService.listarPersonas();// se guarda en la lista persnas y metodo se manda a llamar por la variable perosnaService
        log.debug("personas recuperadas en ManagedBean:" + this.personas);
        this.personaSeleccionada = new Persona();
    }
    //metodo qeu se activa en el index como evento que identifica cuando se edita una celda
    public void editListener(RowEditEvent event){
        Persona persona = (Persona) event.getObject();// identifica el objeto y se castea a persona para ser usado
        personaService.modificarPersona(persona);//llama el metodo de modificar
    }

   //metodos para modificar registros
    
    public void agregarPersona(){
        this.personaService.registrarPersona(personaSeleccionada); //llama al metodo por interface
        this.personas.add(personaSeleccionada); // agrega a la lisya personas
        this.personaSeleccionada = null;
    }
    
    public void eliminarPersona(){
        this.personaService.eliminarPersona(personaSeleccionada);
        this.personas.remove(this.personaSeleccionada);
        this.personaSeleccionada = null;
    }
    
    public void reiniciarPersonaSeleccionada(){
        this.personaSeleccionada = new Persona(); // al atributo de persona seleccionada se asigna nueva persona
    }
    
    //getter y setter    
      public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
}
