package com.my.config;

import com.my.filters.interceptors.AuthAccessInterceptor;
import com.my.filters.interceptors.GuestAccessInterceptor;
import com.my.filters.interceptors.StudentAccessInterceptor;
import com.my.filters.interceptors.TeacherAccessInterceptor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.my")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;
    private final TeacherAccessInterceptor teacherAccessInterceptor;
    private final GuestAccessInterceptor guestAccessInterceptor;
    private final StudentAccessInterceptor studentAccessInterceptor;
    private final AuthAccessInterceptor authAccessInterceptor;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext, TeacherAccessInterceptor teacherAccessInterceptor, GuestAccessInterceptor guestAccessInterceptor, StudentAccessInterceptor studentAccessInterceptor, AuthAccessInterceptor authAccessInterceptor) {
        this.applicationContext = applicationContext;
        this.teacherAccessInterceptor = teacherAccessInterceptor;
        this.guestAccessInterceptor = guestAccessInterceptor;
        this.studentAccessInterceptor = studentAccessInterceptor;
        this.authAccessInterceptor = authAccessInterceptor;
    }

    @Bean
    public SessionFactory sessionFactory() {
        return HibernateConfig.getSessionFactory();
    }

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("fhdhdhhshsfbfhdjejnd@gmail.com");
        mailSender.setPassword("xrcrttsrlcqrhfth");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public ViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authAccessInterceptor).addPathPatterns("/tasks")
                .addPathPatterns("/students")
                .addPathPatterns("/student/**")
                .addPathPatterns("/teacher/**");
        registry.addInterceptor(teacherAccessInterceptor).addPathPatterns("/teacher/**");
        registry.addInterceptor(studentAccessInterceptor).addPathPatterns("/student/**");
        registry.addInterceptor(guestAccessInterceptor).addPathPatterns("/guest/**");
    }
}

