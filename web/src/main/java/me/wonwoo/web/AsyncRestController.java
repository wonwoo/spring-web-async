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

  @GetMapping("/listenable")
  public ListenableFuture<List<Person>> listenablePerson() {
    return asyncService.asyncListenablePersons();
  }

  @GetMapping("/completable")
  public CompletableFuture<List<Person>> completablePerson() {
    return asyncService.asyncCompletablePersons();
  }

}
