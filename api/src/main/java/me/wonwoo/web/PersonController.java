package me.wonwoo.web;

import me.wonwoo.domain.Person;
import me.wonwoo.generator.GeneratorPersons;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

  private final GeneratorPersons generatorPersons;

  public PersonController(GeneratorPersons generatorPersons) {
    this.generatorPersons = generatorPersons;
  }

  @GetMapping("/persons")
  public List<Person> persons() {
    return generatorPersons.getPersons();
  }
}
