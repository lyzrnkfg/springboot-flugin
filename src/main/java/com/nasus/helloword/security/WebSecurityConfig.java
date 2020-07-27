/*

package com.nasus.helloword.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {

            */
/**
             *
             * @param charSequence 用户登录时候传入的密码
             * @return
             *//*

            @Override
            public String encode(CharSequence charSequence) {
                // jiami fangshi
                // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                // String result = encoder.encode("123");	//注册时，对密码123加密，结果将保存到数据库
                // encoder.matches("123", result);
                return charSequence.toString();
            }

            */
/**
             *
             * @param charSequence 用户登录时候传入的密码
             * @param s 数据库中的密码，如果数据库没有该对象则赋值为"userNotFoundPassword"
             * @return
             *//*


            @Override
            public boolean matches(CharSequence charSequence, String s) {
                // s是数据库中的密码，charSequence是用户输入的密码，如果返回是false，依旧不会登录成功，返回true才可以。
                return s.equals(charSequence.toString());
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 如果有允许匿名的url，填在下面
//                .antMatchers().permitAll()
                // 设置 SpringSecurity 对 / 和 "/login" 路径不拦截
                .mvcMatchers("/","/login").permitAll()
                .anyRequest().authenticated()
                .and()
                // 设置登陆页
                // post请求的/login会交给spring security处理登入功能
                //
                // 设置页面传的name值
                .formLogin().usernameParameter("username").passwordParameter("password").loginPage("/loginA")
                // 设置登陆成功页
                .defaultSuccessUrl("/").permitAll()
                // 自定义登陆用户名和密码参数，默认为username和password
//                .usernameParameter("username")
//                .passwordParameter("password")
                .and()
                .logout()
                .permitAll();

        // 关闭CSRF跨域
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // /resource/static 目录下的静态资源，Spring Security 不拦截
        web.ignoring().antMatchers("/resource/static**");
    }
}
*/
