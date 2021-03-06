package me.jpomykala.starters.springhoc.utils;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Java6Assertions.assertThat;

class RequestUtilsTest {

  @Test
  void getClientRemoteAddress() {
    //given
    MockHttpServletRequest mockServerHttpRequest = new MockHttpServletRequest();
    mockServerHttpRequest.setServerName("https://example.com");
    mockServerHttpRequest.setRequestURI("/example");
    mockServerHttpRequest.setLocalAddr("1.1.1.1");
    mockServerHttpRequest.setRemoteAddr("2.2.2.2");

    //when
    String clientIP = RequestUtils.getClientIP(mockServerHttpRequest);

    //then
    assertThat(clientIP).isEqualTo("2.2.2.2");
  }

  @Test
  void getClientXForwardedFor() {
    //given
    MockHttpServletRequest mockServerHttpRequest = new MockHttpServletRequest();
    mockServerHttpRequest.setServerName("https://example.com");
    mockServerHttpRequest.setRequestURI("/example");
    mockServerHttpRequest.setLocalAddr("1.1.1.1");
    mockServerHttpRequest.setRemoteAddr("2.2.2.2");
    mockServerHttpRequest.addHeader("X-Forwarded-For", "3.3.3.3");

    //when
    String clientIP = RequestUtils.getClientIP(mockServerHttpRequest);

    //then
    assertThat(clientIP).isEqualTo("3.3.3.3");
  }

  @Test
  void getPath() {
    //given
    MockHttpServletRequest mockServerHttpRequest = new MockHttpServletRequest();
    mockServerHttpRequest.setServerName("example.com");
    mockServerHttpRequest.setRequestURI("/example");
    mockServerHttpRequest.setQueryString("param1=a&param2=b");

    //when
    String path = RequestUtils.getPath(mockServerHttpRequest);

    //then
    assertThat(path).isEqualTo("http://example.com/example");
  }
}
