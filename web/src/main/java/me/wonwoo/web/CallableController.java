package me.wonwoo.web;

import me.wonwoo.domain.Person;
import me.wonwoo.generator.GeneratorPersons;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RestController
public class CallableController {

  private final GeneratorPersons generatorPersons;

  public CallableController(GeneratorPersons generatorPersons) {
    this.generatorPersons = generatorPersons;
  }

  @GetMapping("/callable")
  public Callable<List<Person>> persons(){
    return () -> {
      TimeUnit.SECONDS.sleep(2);
      return generatorPersons.getPersons();
    };
  }

  @GetMapping("/callable/{name}")
  public Callable<List<Person>> person(@PathVariable String name){
    return () -> {
      TimeUnit.SECONDS.sleep(2);
      return generatorPersons.getPersons();
    };
  }
}
