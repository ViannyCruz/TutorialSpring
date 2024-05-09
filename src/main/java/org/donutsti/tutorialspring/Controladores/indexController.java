package org.donutsti.tutorialspring.Controladores;

import org.springframework.web.bind.annotation.GetMapping;

// indexController
// Lo unico que hare aqui sera rutar la pagina inicial
// Pero, ciertamnete se podria tener que el controlador rute mas cosas
public class indexController {

    // @GetMapping("/")
    // Este método responderá a las solicitudes HTTP GET en la ruta raíz del servidor ("/").
    // En otras palabras, cuando alguien acceda a la URL base, este método se ejecutará.
    @GetMapping("/")
    public String mostrarImagen() { // Cuando se acceda a la URL base, se ejecutara esta funcion
        // Renderizar el index o Redirigir al index
        return "../static/index.html"; // Ruta al index.html
    }

}
