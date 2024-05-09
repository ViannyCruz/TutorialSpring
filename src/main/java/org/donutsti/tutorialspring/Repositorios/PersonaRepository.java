package org.donutsti.tutorialspring.Repositorios;

import org.donutsti.tutorialspring.Entidades.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PersonaRepository extends MongoRepository<Persona, String> {

}
