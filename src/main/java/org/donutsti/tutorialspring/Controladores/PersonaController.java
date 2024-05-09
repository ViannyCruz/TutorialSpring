package org.donutsti.tutorialspring.Controladores;
import org.donutsti.tutorialspring.Entidades.Persona;
import org.donutsti.tutorialspring.Repositorios.PersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PersonaController {


    // "Heredamos" del repositorio que a su vez hereda de mongo
    @Autowired
    private PersonaRepository personaRepository;


    // @PostMapping("/CrearPersona")
    // Este es un método de controlador que maneja las solicitudes POST en la ruta "/CrearPersona".
    // El mismo post que haciamos antes, solo que aqui se ve diferente
    @PostMapping("/CrearPersona") // Se recibira un objeto persona en este caso
    public ResponseEntity<Map<String, String>> realizarPersona(@RequestBody Persona persona) {
        try {
            System.out.println("CREAR PERSONA");
            // y de donde viene ese objeto persona?
            // se construye en el javascript, vea

            // Podemos imprimir la informacion obtenida
            System.out.println("Nombre: " + persona.getNombre());
            System.out.println("Apellido: " + persona.getApellido());

            // Intentar guardar el pedido en la base de datos
            Persona personaGuardada = personaRepository.save(persona);

            // Imprimir en la consola el pedido guardado
            System.out.println("Pedido guardado: " + personaGuardada);




            // Crear una respuesta de éxito para el cliente
            Map<String, String> response = new HashMap<>();
            response.put("message", "Pedido realizado correctamente");
            return ResponseEntity.ok(response);



        } catch (Exception e) {

            // Manejo de excepciones
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al procesar la persona");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    // @GetMapping("/personas")
    // Tipico get de toda la vida pero que ahora se ve algo diferente
    @GetMapping("/personas") // Responde a la ruta /personas
    public ResponseEntity<List<Persona>> listarPersonas() {
        List<Persona> pedidos = personaRepository.findAll();
        return ResponseEntity.ok(pedidos); // En este caso, retornaremos todos los pedidos
    }


    // @GetMapping("/personas/{id}")
    // Otro get, ahora mas especifico porque busca una persona por id
    @GetMapping("/personas/{id}")
    public Map<String, Optional<Persona>> obtenerPersonaPorId(@PathVariable String id){

        Optional<Persona> personaOptional = personaRepository.findById(id);

        // Crear el mapa con el costo
        Map<String, Optional<Persona>> response = new HashMap<>();
        response.put("persona", personaOptional);

        return response;
    }


    // @DeleteMapping("/personas/{id}")
    // Bastante sencillo, se elimina una persona segun el id
    @DeleteMapping("/personas/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable String id) {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (personaOptional.isPresent()) {
            personaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }












    // @PutMapping("/personas/{id}")
// Este método maneja las solicitudes PUT para actualizar una persona existente por su ID.
    @PutMapping("/personas/{id}")
    public ResponseEntity<Map<String, String>> modificarPersona(@PathVariable String id, @RequestBody Persona personaNueva) {
        try {
            // Verificar si la persona con el ID dado existe en la base de datos
            Optional<Persona> personaOptional = personaRepository.findById(id);
            if (personaOptional.isPresent()) {
                // Actualizar los datos de la persona existente con los nuevos datos
                Persona personaExistente = personaOptional.get();
                personaExistente.setNombre(personaNueva.getNombre());
                personaExistente.setApellido(personaNueva.getApellido());

                // Guardar la persona actualizada en la base de datos
                Persona personaActualizada = personaRepository.save(personaExistente);

                // Crear una respuesta de éxito para el cliente
                Map<String, String> response = new HashMap<>();
                response.put("message", "Persona modificada correctamente");
                return ResponseEntity.ok(response);
            } else {
                // Si la persona no existe, devolver una respuesta de error
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Persona no encontrada");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al procesar la solicitud de modificación de persona");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }















    // Nota: Notese que hay varias funciones asociadas a un mismo endpoint, o al menos asi parece
    // el endpoint "/personas/{id}"
    // ciertamente, se podria decir que es el mismo endpoint, lo que hace posible que se usen las
    // funciones correspondientes son la especie de prefijos como: @DeleteMapping, @PutMapping y @GetMapping


}
