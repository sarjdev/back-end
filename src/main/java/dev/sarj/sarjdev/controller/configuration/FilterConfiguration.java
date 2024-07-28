package dev.sarj.sarjdev.controller.configuration;

import dev.sarj.sarjdev.controller.filter.AccessLogFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfiguration {
    private final AccessLogFilter accessLogFilter;

    @Bean
    public FilterRegistrationBean<AccessLogFilter> accessLogFilterRegistrationBean() {
        FilterRegistrationBean<AccessLogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(accessLogFilter);
        registrationBean.addUrlPatterns("/v1/*");
        registrationBean.setName("accessLogFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
