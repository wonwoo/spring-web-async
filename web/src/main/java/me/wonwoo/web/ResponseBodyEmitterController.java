package me.wonwoo.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class ResponseBodyEmitterController {

  @GetMapping("/emitter")
  public ResponseBodyEmitter emitter() {
    ResponseBodyEmitter emitter = new ResponseBodyEmitter();
    ExecutorService service = Executors.newSingleThreadExecutor();
    service.submit(() -> {
      for (int i = 0; i < 10; i++) {
        emitter.send("<p> Stream index : " + i + " </p>");
        Thread.sleep(100);
      }
      emitter.complete();
      return null;
    });
    return emitter;
  }
}
