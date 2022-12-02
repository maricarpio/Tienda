/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserDetailsService userDetailsService;
    //Este metodo permite la autenticacion de usuario.. usuarios en memoria
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        /*auth.inMemoryAuthentication()
                .withUser("Juan")
                .password("{noop}123")
                .roles("ADMIN","VENDEDOR","USER")
                .and()
                .withUser("Rebeca")
                .password("{noop}456")
                .roles("VENDEDOR","USER")
                .and()
                .withUser("Pedro")
                .password("{noop}789")
                .roles("USER"); */
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(new BCryptPasswordEncoder());
    }
    
    //Este metodo permite la autorizacion a los recursos del sitio web
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
                .antMatchers("/cliente/nuevo","cliente/guardar",
                        "/cliente/modificar/**","/cliente/eliminar/**",
                        "/categoria/nuevo","categoria/guardar",
                        "/categoria/modificar/**","/categoria/eliminar/**",
                        "/articulo/nuevo","articulo/guardar",
                        "/articulo/modificar/**","/articulo/eliminar/**",
                        "/usuario/nuevo","usuario/guardar",
                        "/usuario/modificar/**","/usuario/eliminar/**")
                        .hasRole("ADMIN")
                        .antMatchers("/cliente/listado",
                                "/categoria/listado",
                                "/articulo/listado")
                .hasAnyRole("ADMIN","VENDEDOR")
                .antMatchers("/")
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/errores/403");           
    }
}
