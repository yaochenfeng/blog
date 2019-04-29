package cn.bestws.app.commom.config;

import cn.bestws.app.modules.auth.entity.User;
import cn.bestws.app.modules.auth.repository.UserRepository;
import cn.bestws.app.modules.auth.service.AuthUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

//@EnableWebSecurity
@Configuration
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        注册intercept url规则权限匹配信息
        /*options请求 全部不拦截*/
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().logout().permitAll();
//        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers(
//                        "/",
//                        "/v2/api-docs",
//                        "/swagger-resources/**",
//                        "/swagger-ui.html**",
//                        "/webjars/**",
//                        "favicon.ico",
//                        "/actuator/**"
//                ).permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/v2/api-docs",
                "/swagger-resources/configuration/ui",
                "/swagger-resources",
                "/swagger-resources/configuration/security",
                "/swagger-ui.html",
                "/css/**",
                "/js/**",
                "/img/**"
        );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(bCryptPasswordEncoder());
        //基于内存的用户存储、认证
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("admin").roles("ADMIN","USER")
//                .and()
//                .withUser("user").password("user").roles("USER");
    }
    @Bean
    UserDetailsService customUserService() {
        return new AuthUserDetailsService();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    public void initUser(){
        log.info("添加默认用户admin");
        if (userRepository.count() == 0){
            //用户数据
            userRepository.save(new User((long) 1,"admin","admin",bCryptPasswordEncoder().encode("admin")));
        }
    }
    @Autowired
    private UserRepository userRepository;

}
