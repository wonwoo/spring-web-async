package me.wonwoo.web;

import me.wonwoo.testing.Testing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CallableControllerTests {

  @Autowired
  private TestRestTemplate template;

  @Test
  public void callable() throws InterruptedException {
    try (Testing testing = new Testing(100, 100)) {
      testing.run(() -> template.getForEntity("/callable", String.class));
      assertThat(testing.getTotalTimeMillis()).isLessThan(3000);
      System.out.println(testing.getTotalTimeMillis());
      assertThat(testing.getRealCount()).isEqualTo(100);
      assertThat(testing.totalActionCount()).isEqualTo(100);
    }
  }
}