package mx.com.gm.sga.web;

//esta clas ese usa para configurar el bean y la version de JSF q se usara

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import static javax.faces.annotation.FacesConfig.Version.JSF_2_3;

@FacesConfig(
        version=JSF_2_3
)

@ApplicationScoped //indica sera una tipo aplicationscoped par aq funcione a lo largo de toda la aplicacion
public class ConfiguracionBean {
    
}
