package me.jpomykala.starters.springhoc.wrapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ResponseWrappingAutoConfigurationTests {

  private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
          .withConfiguration(AutoConfigurations.of(ResponseWrapper.class));


  @Test
  public void testDefaultCorsConfiguration() {
    this.contextRunner.run(context -> {
      ResponseWrapper responseWrapper = context.getBean(ResponseWrapper.class);
      assertThat(responseWrapper).isNotNull();
    });

  }

}
