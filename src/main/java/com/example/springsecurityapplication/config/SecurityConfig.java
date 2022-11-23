package com.example.springsecurityapplication.config;

import com.example.springsecurityapplication.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServlet;

//2. Прописывается все логика по идентификации и авторизации
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private  final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }
    //Конфигурация Spring Security
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                // указываем что все страницы будут защищены процессом аутентификации
                .authorizeRequests()
                // указываем что страница /admin доступна пользователю с ролью ADMIN
                .antMatchers("/admin").hasRole("ADMIN")
                // Указыаем что данные страницы доступна все пользователям
                .antMatchers("/authentication/login","/authentication/registration", "/error").permitAll()
                // Указываем что все остальные страницы доступны пользователям с ролью user и admin
                .anyRequest().hasAnyRole("USER", "ADMIN")
//                // Указываем что для всех остальных страниц необходимо вызывать метод authenticated(), который открывает форму аутентификации
//                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/authentication/login")
                // указываем на какой url будут отправляться данные с формы аутентификации
                .loginProcessingUrl("/process_login")
                // Указываем на какой url необходимо направить пользователя после успешной аутентификации
                .defaultSuccessUrl("/index", true)
                // Указываем куда необходимо перейти при неверный аутентификации
                .failureUrl("/authentication/login?error")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/authentication/login");
    }

    // Данный метод позволяет настроить аутентификацию
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{

        //Делегируем аутификацию специальному сервису
        authenticationManagerBuilder.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }
    @Bean
    //Отключаем или включает шифрование паролей, включаем
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
