package com.apap.tugasakhir.siruangan.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/user/tambah").hasAuthority("Admin TU")
                .antMatchers("/ruangan/peminjaman").hasAnyAuthority("Admin TU", "Siswa","Guru")
                .antMatchers("/fasilitas/pengadaan").hasAnyAuthority("Admin TU", "Guru")
                .antMatchers("/fasilitas/pengadaan/tambah").hasAnyAuthority("Admin TU", "Guru")
                .and()
                .formLogin()
                .loginPage("/user/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")).logoutSuccessUrl("/").permitAll();
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

}
