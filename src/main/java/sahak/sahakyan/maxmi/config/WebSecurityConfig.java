package sahak.sahakyan.maxmi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig   extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select u.username, a.name from users u join user_authority ua on u.id = ua.user_id join authorities a on ua.authority_id = a.id where u.username=?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/registration","/index")
                .permitAll()
                .antMatchers("/account/**").hasAnyAuthority("USER")
                .and()
                .formLogin().defaultSuccessUrl("/asking")
                .loginPage("/login")
                .failureUrl("/login-error")
                .and()
                .logout().logoutSuccessUrl("/login")
                .permitAll();

    }

   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/asking")
                    .successForwardUrl("/index")
                    .usernameParameter("username")
                    .passwordParameter("password")
                .permitAll()
//                .and()
//                    .antMatcher("/registration").anonymous()
//                .and().antMatcher("/").anonymous()
//                .and().antMatcher("/login").anonymous()
                .and()
                .logout()
                    .logoutUrl("/logout")
                .permitAll();
    }*/
}