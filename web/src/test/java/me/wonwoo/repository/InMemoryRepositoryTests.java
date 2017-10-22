package me.wonwoo.repository;

import me.wonwoo.domain.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


public class InMemoryRepositoryTests {

  private final List<Person> persons = Arrays.asList(
      new Person("wonwoo", "wonwoo@test.com"),
      new Person("kevin", "kevin@test.com")
  );
  private final InMemoryRepository<Person, UUID> inMemoryRepository = new InMemoryRepository<>();

  @Before
  public void setup() {
    this.inMemoryRepository.save(persons);
  }

  @Test
  public void save() {
    InMemoryRepository<Person, UUID> inMemoryRepository = new InMemoryRepository<>();
    Person person = inMemoryRepository.save(persons.get(0));
    assertThat(person.getName()).isEqualTo("wonwoo");
    assertThat(person.getEmail()).isEqualTo("wonwoo@test.com");
  }

  @Test
  public void findOne() {
    InMemoryRepository<Person, UUID> inMemoryRepository = new InMemoryRepository<>();
    Person person = inMemoryRepository.save(persons.get(0));
    Person findOne = inMemoryRepository.findOne(person.getId());
    assertThat(findOne.getName()).isEqualTo("wonwoo");
    assertThat(findOne.getEmail()).isEqualTo("wonwoo@test.com");
  }

  @Test
  public void delete() {
    InMemoryRepository<Person, UUID> inMemoryRepository = new InMemoryRepository<>();
    Person person = inMemoryRepository.save(persons.get(0));
    inMemoryRepository.delete(person);
    assertThat(inMemoryRepository.findAll()).hasSize(0);
  }

  @Test
  public void deleteAll() {
    this.inMemoryRepository.deleteAll(persons);
    assertThat(this.inMemoryRepository.findAll()).hasSize(0);
  }
}