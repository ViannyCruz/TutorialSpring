package org.donutsti.tutorialspring.Entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "personas")
public class Persona {

    @Id
    private String id;
    private String nombre;
    private String apellido;




    // ID
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Setter para el nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para el nombre
    public String getNombre() {
        return nombre;
    }

    // Setter para el apellido
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Getter para el apellido
    public String getApellido() {
        return apellido;
    }

}
