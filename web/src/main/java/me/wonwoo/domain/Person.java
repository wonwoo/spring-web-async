package me.wonwoo.domain;

import me.wonwoo.repository.Entity;

import java.util.UUID;

public class Person implements Entity<UUID> {

  private String name;
  private String email;
  private UUID id;

  Person() {

  }

  public Person(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public void setId(UUID id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", id=" + id +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Person person = (Person) o;

    return id != null ? id.equals(person.getId()) : person.getId() == null;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
