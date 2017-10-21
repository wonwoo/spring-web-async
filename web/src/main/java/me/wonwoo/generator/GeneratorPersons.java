package me.wonwoo.generator;

import me.wonwoo.domain.Person;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class GeneratorPersons {

  private final List<Person> persons = Arrays.asList(
      new Person("wonwoo", "wonwoo@test.com"),
      new Person("kevin", "kevin@test.com"));

  public List<Person> getPersons() {
    return persons;
  }

  public Person getPerson(String name) {
    return persons
        .stream()
        .filter(person -> person.getName().equals(name))
        .findFirst().orElse(null);
  }
}
