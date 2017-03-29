package com.kchen.storage.gateway.config;


import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanInjector;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Spring Cloud Sleuth does not add trace/span related headers to the Http Response for security reasons
 */
@Configuration
public class SleuthConfig {

    private static class CustomHttpServletResponseSpanInjector
            implements SpanInjector<HttpServletResponse> {

        @Override
        public void inject(Span span, HttpServletResponse carrier) {
            carrier.addHeader("Uuid", span.traceIdString());
        }
    }

    private static class HttpResponseInjectingTraceFilter extends GenericFilterBean {

        private final Tracer tracer;
        private final SpanInjector<HttpServletResponse> spanInjector;

        public HttpResponseInjectingTraceFilter(Tracer tracer, SpanInjector<HttpServletResponse> spanInjector) {
            this.tracer = tracer;
            this.spanInjector = spanInjector;
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            Span currentSpan = this.tracer.getCurrentSpan();
            this.spanInjector.inject(currentSpan, response);
            filterChain.doFilter(request, response);
        }
    }

    @Bean
    SpanInjector<HttpServletResponse> customHttpServletResponseSpanInjector() {
        return new CustomHttpServletResponseSpanInjector();
    }

    @Bean
    HttpResponseInjectingTraceFilter responseInjectingTraceFilter(Tracer tracer) {
        return new HttpResponseInjectingTraceFilter(tracer, customHttpServletResponseSpanInjector());
    }
}
