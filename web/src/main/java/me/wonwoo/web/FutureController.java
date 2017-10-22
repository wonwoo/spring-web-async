package me.wonwoo.web;

import me.wonwoo.domain.Person;
import me.wonwoo.generator.GeneratorPersons;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

@RestController
public class FutureController {

  private final GeneratorPersons generatorPersons;

  public FutureController(GeneratorPersons generatorPersons) {
    this.generatorPersons = generatorPersons;
  }

  @GetMapping("/future")
  public Future<List<Person>> future() {
    return CompletableFuture.supplyAsync(generatorPersons::getPersons);
  }

  @GetMapping("/completionStage")
  public CompletionStage<List<Person>> completionStage() {
    return CompletableFuture.supplyAsync(generatorPersons::getPersons);
  }
}
