package me.wonwoo.web;

import me.wonwoo.domain.Person;
import me.wonwoo.generator.GeneratorPersons;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.List;
import java.util.concurrent.CompletionStage;

@RestController
public class WebAsyncTaskController {

  private final GeneratorPersons generatorPersons;

  public WebAsyncTaskController(GeneratorPersons generatorPersons) {
    this.generatorPersons = generatorPersons;
  }

  @GetMapping("/webAsyncTask")
  public WebAsyncTask<List<Person>> webAsyncTaskPerson() {
    return new WebAsyncTask<>(generatorPersons::getPersons);
  }
}
