package com.isp.billing.conf;

import com.isp.billing.model.Role;
import com.isp.billing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Toufiq on 7/25/2019.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private DataSource dataSource;

    private static final String[] IGNORED_RESOURCE_LIST = new String[]{
            "/webjars/**", "/css/**", "/js/**", "/images/**"
    };


    private static final String[] PERMITALL_RESOURCE_LIST = new String[]{
            "/", "/index", "/registration", "/get_any_image/**", "/help", "/about", "/login", "/logout"};


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();

    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authProvider;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {



        http.
                authorizeRequests()
                .antMatchers(PERMITALL_RESOURCE_LIST).permitAll();


        http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll().antMatchers("/admin/**").hasAnyAuthority(getRoles()).anyRequest()
                .authenticated().and().csrf().disable().formLogin().loginPage("/login").successHandler(successHandler())//successHandler()//defaultSuccessUrl("/main", true) //
                .failureUrl("/login?error=true")
                .usernameParameter("username").passwordParameter("password").and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").and()
                .exceptionHandling().accessDeniedPage("/access-denied");


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                //Spring Security ignores request to static resources such as CSS or JS files.
                .ignoring()
                .antMatchers(IGNORED_RESOURCE_LIST);
    }

    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }


    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler
    savedRequestAwareAuthenticationSuccessHandler() {

        SavedRequestAwareAuthenticationSuccessHandler auth
                = new SavedRequestAwareAuthenticationSuccessHandler();
        auth.setTargetUrlParameter("targetUrl");
//        this.refererAuthenticationSuccessHandler.setUpUserForm1();
        return auth;
    }

    @Bean
    public AuthenticationSuccessHandler successHandler(){
        return new RefererAuthenticationSuccessHandler();
    }

    public String getRoles()
    {
        String ars1="" ;
//        String ars[] = new String[this.userService.findAllRole().size()];
        int i=0;
        List<Role> roles =this.userService.findAllRole();
        for(Role r : roles)
        {
            ++i;
            if (i==1)
            {
                ars1='"'+r.getRole()+'"';
            }else
            {

                ars1=ars1+','+'"'+r.getRole()+'"';
            }
//            ++i;
//            ars[i]=r.getRole();
        }
//        System.out.println(ars1);
        return ars1;

    }

}
