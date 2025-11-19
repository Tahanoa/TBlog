package org.example.tblog.config;

import org.example.tblog.service.UsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UsersService usersService;

    public SecurityConfig(UsersService usersService) {
        this.usersService = usersService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // دسترسی کاملاً عمومی - صفحات استاتیک و لاگین
                        .requestMatchers(
                                "/login",
                                "/",
                                "/**.png",
                                "/**.css",
                                "/**.js",
                                "/webjars/**"
                        ).permitAll()

                        // دسترسی عمومی فقط برای خواندن مطالب
                        .requestMatchers(
                                "/api/posts/{id}",
                                "/api/posts",
                                "/api/posts/search",
                                "/api/posts/category/{categoryId}",
                                "/api/categories/{id}",
                                "/api/categories",
                                "/api/categories/search",
                                "/api/tags/search",
                                "/api/tags/post/{postId}",
                                "/api/comments/{id}",
                                "/api/comments/count/post/{postId}",
                                "/api/comments/status/{status}",
                                "/api/comments/post/{postId}/status/{status}"
                        ).permitAll()

                        // دسترسی برای کاربران عادی (لاگین شده)
                        .requestMatchers(
                                "/api/comments" // POST - ایجاد کامنت جدید
                        ).hasAnyRole("USER", "ADMIN")

                        // دسترسی فقط برای ادمین - API های حساس
                        .requestMatchers(
                                "/api/posts", // POST - ایجاد پست جدید
                                "/api/posts/*", // PUT, DELETE - آپدیت و حذف پست
                                "/api/categories", // POST - ایجاد دسته‌بندی
                                "/api/categories/*", // PUT, DELETE - آپدیت و حذف دسته‌بندی
                                "/api/tags", // POST - ایجاد تگ
                                "/api/tags/*", // DELETE - حذف تگ
                                "/api/comments/*/status", // PUT - تغییر وضعیت کامنت
                                "/api/comments/*" // DELETE - حذف کامنت
                        ).hasRole("ADMIN")

                        // سایر درخواست‌ها نیاز به احراز هویت دارند
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(usersService);
        return authProvider;
    }
}