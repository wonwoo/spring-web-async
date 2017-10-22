package me.wonwoo.service;

import me.wonwoo.domain.Person;
import me.wonwoo.generator.GeneratorPersons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final GeneratorPersons generatorPersons;

  public AsyncService(GeneratorPersons generatorPersons) {
    this.generatorPersons = generatorPersons;
  }

  @Async
  public ListenableFuture<List<Person>> asyncListenablePersons() {
    logger.info("asyncListenablePersons");
    return new AsyncResult<>(generatorPersons.getPersons());
  }

  @Async
  public CompletableFuture<List<Person>> asyncCompletablePersons() {
    logger.info("asyncCompletablePersons");
    return CompletableFuture.supplyAsync(generatorPersons::getPersons);
  }
}
