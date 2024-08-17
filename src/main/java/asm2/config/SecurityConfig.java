package asm2.config;

import asm2.service.CustomUserDetailsService;
import asm2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource securityDataSource;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/asm2/home").permitAll()
                .antMatchers("/posts/detailsrecruit/**").permitAll()
                .antMatchers("/posts/searchJob/**").permitAll()
                .antMatchers("/posts/searchLocation/**").permitAll()
                .antMatchers("/posts/searchCompany/**").permitAll()
                .antMatchers("/profile/**").hasAuthority("COMPANY")
                .antMatchers("/profileUser/**").hasAuthority("EMPLOYEE")
                .antMatchers("/posts/**").hasAuthority("COMPANY")
                .antMatchers("/usersByCompany/**").hasAuthority("COMPANY")
                .antMatchers("/systems/**").hasAuthority("ADMIN")

//                .antMatchers("/asm2/home","/login", "/logout", "/access-denied").permitAll()
//                .anyRequest().authenticated() // Tất cả các request khác cần xác thực

                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied")
                .and()
                .csrf().disable(); // Tắt CSRF protection
    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        // use jdbc Authentication
//            auth.jdbcAuthentication().dataSource(securityDataSource);
//    }



}
