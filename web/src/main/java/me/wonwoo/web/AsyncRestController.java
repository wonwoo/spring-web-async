package me.wonwoo.web;

import me.wonwoo.domain.Person;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.List;

@RestController
public class AsyncRestController {

  private final AsyncRestTemplate asyncRestTemplate;

  public AsyncRestController(AsyncRestTemplate asyncRestTemplate) {
    this.asyncRestTemplate = asyncRestTemplate;
  }

  @GetMapping("/async")
  public ListenableFuture<ResponseEntity<List<Person>>> persons() {
    return asyncRestTemplate.exchange("http://localhost:8081/persons",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Person>>() {});
  }
}
