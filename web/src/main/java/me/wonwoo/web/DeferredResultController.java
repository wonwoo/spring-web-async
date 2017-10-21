package me.wonwoo.web;

import me.wonwoo.domain.Person;
import me.wonwoo.generator.GeneratorPersons;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@RestController
public class DeferredResultController {

  private final Queue<DeferredResult<List<Person>>> personsQueue = new ConcurrentLinkedQueue<>();

  private final GeneratorPersons generatorPersons;

  public DeferredResultController(GeneratorPersons generatorPersons) {
    this.generatorPersons = generatorPersons;
  }

  @GetMapping("/deferred")
  public DeferredResult<List<Person>> persons() {
    DeferredResult<List<Person>> result = new DeferredResult<>();
    personsQueue.add(result);
    return result;
  }

  @Scheduled(fixedRate = 2000)
  public void processQueues() {
    for (DeferredResult<List<Person>> result : this.personsQueue) {
      result.setResult(generatorPersons.getPersons());
      this.personsQueue.remove(result);
    }
  }
}
