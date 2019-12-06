package com.apap.tugasakhir.siruangan.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                .antMatchers("/fasilitas/pengadaan/hapus").hasAnyAuthority("Admin TU", "Guru")
                .antMatchers("/fasilitas/tambah").hasAuthority("Admin TU")
                .antMatchers("/fasilitas/ubah/**").hasAuthority("Admin TU")
                .antMatchers("/fasilitas/hapus/**").hasAuthority("Admin TU")
                /*.antMatchers("/ruangan/peminjaman/tambah").hasAnyAuthority( "Siswa","Guru")*/
                /*.antMatchers("/fasilitas/ubah/{\\\\d+}").hasAuthority("Admin TU")
                .antMatchers("/fasilitas/hapus/{\\\\d+}").hasAuthority("Admin TU")*/
                .antMatchers("/ruangan/peminjaman/tambah/**").hasAnyAuthority( "Siswa","Guru")
                .antMatchers("/ruangan/peminjaman/ubah").hasAuthority("Admin TU")
                .antMatchers("/fasilitas/pengadaan/buku").hasAuthority("Admin TU")
                .antMatchers(HttpMethod.POST,"/api/ruangan/peminjaman/tambah").permitAll()
                .and()
                .formLogin()
                .loginPage("/user/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")).logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().disable();
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
