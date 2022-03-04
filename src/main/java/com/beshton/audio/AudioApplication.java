package com.beshton.audio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.beshton.audio")
//public class ShopApplication extends WebSecurityConfigurerAdapter {
public class AudioApplication {
//	@RequestMapping("/user")
//	public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
//		return Collections.singletonMap("name", principal.getAttribute("name"));
//	}
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(ShopApplication.class);
//	}

//	@RequestMapping("/wemp")
//	public String wemp(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//		model.addAttribute("name", name);
//		return "WebEmployee";
//	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// @formatter:off
//		http
//				.authorizeRequests(a -> a
//						.antMatchers("/", "/error", "/webjars/**").permitAll()
//						.anyRequest().authenticated()
//				)
//				.exceptionHandling(e -> e
//						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//				)
//				.csrf(c -> c
//				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//				.logout(l -> l
//						.logoutSuccessUrl("/").permitAll()
//				)
//				.oauth2Login();
//		// @formatter:on
//	}

	public static void main(String[] args) {
		SpringApplication.run(AudioApplication.class, args);
	}

}
