package me.jpomykala.starters.springhoc.logging;

import me.jpomykala.starters.springhoc.utils.RequestUtils;
import org.springframework.lang.NonNull;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class LoggingFilterFactory {

  private RequestIdProvider requestIdProvider = new DefaultRequestIdProvider();
  private PrincipalProvider principalProvider = new DefaultPrincipalProvider();

  private String mdcKey = "user";
  private String logFormat = "[u:%s][rid:%s]";

  @NotNull
  public LoggingFilterFactory withCustomMdc(@NotNull String mdcKey, @NotNull String logFormat) {
    this.mdcKey = mdcKey;
    this.logFormat = logFormat;
    return this;
  }

  @NotNull
  public LoggingFilterFactory withRequestIdProvider(@NonNull RequestIdProvider requestIdProvider) {
    this.requestIdProvider = requestIdProvider;
    return this;
  }

  @NotNull
  public LoggingFilterFactory withPrincipalProvider(@NotNull PrincipalProvider principalProvider) {
    this.principalProvider = principalProvider;
    return this;
  }

  @NotNull
  public LoggingFilter createFilter() {
    LoggingFilter loggingFilter = new LoggingFilter(mdcKey, logFormat);
    loggingFilter.setPrincipalProvider(principalProvider);
    loggingFilter.setRequestIdProvider(requestIdProvider);
    return loggingFilter;
  }


  private class DefaultRequestIdProvider implements RequestIdProvider {
    @Override
    public String getRequestId(HttpServletRequest request) {
      return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }
  }

  private class DefaultPrincipalProvider implements PrincipalProvider {
    @Override
    public String getPrincipal(HttpServletRequest request) {
      return RequestUtils.getClientIP(request);
    }
  }
}
