package com.nasus.helloword.bean;

import com.nasus.helloword.filter.MyFilter;
import com.nasus.helloword.listener.MyServletContextListener;
import com.nasus.helloword.servlet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.reflect.Method;
import java.util.Arrays;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    //定义不拦截路径
    private static final String[] excludePaths = {"/", "/index", "/index.html", "/user/login", "/asserts/**"};

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }
    
    // i18n 在配置类中将其注册到容器中
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleReslover();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加不拦截的路径，SpringBoot已经做好了静态资源映射，所以我们不用管
        registry.addInterceptor(new LoginHandlerInterceptor())
                .excludePathPatterns(excludePaths);
    }

    // 编写一个EmbeddedServletContainerCustomizer，2.0以后改为WebServerFactoryCustomizer：嵌入式的Servlet容器的定制器；来修改Servlet容器的配置
    @Bean
    public WebServerFactoryCustomizer webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                //factory.setPort(8088);
            }
        };
    }

    // 注册三大组件
    /*@Bean
    public ServletRegistrationBean myServlet() {
        // MyServlet自定义的类
        ServletRegistrationBean register = new ServletRegistrationBean(new MyServlet(), "/myServlet");
        register.setLoadOnStartup(1);
        return register;
    }

    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean register = new FilterRegistrationBean(new MyFilter());
        register.setUrlPatterns(Arrays.asList("/myServlet","/"));
        return register;
    }

    @Bean
    public ServletListenerRegistrationBean myServletContextListener(){
        return new ServletListenerRegistrationBean(new MyServletContextListener());
    }*/

    @Bean("myKeyGenerator")
    public KeyGenerator myKeyGenerator() {
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return method.getName()+"["+ Arrays.asList(params).toString()+target+"]";
            }
        };
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*") //允许跨域的域名，可以用*表示允许任何域名使用 allowedOrigins("http://localhost:8080/")
                        .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH") //允许任何方法（post、get等） 可以用*表示允许任何
                        .allowCredentials(true) //带上cookie信息
                        .exposedHeaders(HttpHeaders.SET_COOKIE).maxAge(3600); //maxAge(3600)表明在3600秒内，不需要再发送预检验请求，可以缓存该结果
            }
        };
    }

}
