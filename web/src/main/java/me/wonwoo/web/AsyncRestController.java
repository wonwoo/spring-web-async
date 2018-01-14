package me.wonwoo.web;


import me.wonwoo.domain.Person;
import me.wonwoo.service.AsyncService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class AsyncRestController {

  private final AsyncRestTemplate asyncRestTemplate;
  private final AsyncService asyncService;

  public AsyncRestController(AsyncRestTemplate asyncRestTemplate,
                             AsyncService asyncService) {
    this.asyncRestTemplate = asyncRestTemplate;
    this.asyncService = asyncService;
  }

  @GetMapping("/async")
  public ListenableFuture<ResponseEntity<List<Person>>> persons() {
    return asyncRestTemplate.exchange("http://localhost:8081/persons",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Person>>() {});
  }
  @GetMapping("/completableFuture")
  public CompletableFuture<ResponseEntity<List<Person>>> persons1() {
    return toListenableFuture(asyncRestTemplate.exchange("http://localhost:8081/persons",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Person>>() {}));
  }

  private <T> CompletableFuture<T> toListenableFuture(ListenableFuture<T> lf) {
    CompletableFuture<T> future = new CompletableFuture<>();
    lf.addCallback(future::complete, future::obtrudeException);
    return future;
  }

  @GetMapping("/listenable")
  public ListenableFuture<List<Person>> listenablePerson() {
    return asyncService.asyncListenablePersons();
  }

  @GetMapping("/completable")
  public CompletableFuture<List<Person>> completablePerson() {
    return asyncService.asyncCompletablePersons();
  }

}
