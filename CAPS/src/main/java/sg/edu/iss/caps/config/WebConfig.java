package sg.edu.iss.caps.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import sg.edu.iss.caps.interceptor.AuthenticateInterceptor;

@Component
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	AuthenticateInterceptor authInterceptor;
	
	
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor);
	
	}
}
