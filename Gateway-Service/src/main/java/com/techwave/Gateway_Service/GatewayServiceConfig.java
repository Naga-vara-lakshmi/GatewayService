package com.techwave.Gateway_Service;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayServiceConfig {
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p->p.path("/springsecuritydemo/**")
						.filters(f->f.rewritePath("/springsecuritydemo/(?<segment>.*)", "/${segment}"))
						.uri("lb://springsecuritydemo"))
				
				.route(p->p.path("/getAllProducts/**")
						.uri("lb://ProductService"))
				
				.route(p->p.path("/getUserById/**")
						.uri("lb://ProductService"))
				
				.route(p -> p.path("/productservice/**")
					    .filters(f -> f.rewritePath("/productservice/(?<segment>.*)", "/${segment}"))
					    .uri("lb://productservice"))
				
				.route(p->p.path("/user/**")
						.uri("lb://springsecuritydemo"))
				.route(p->p.path("/product/**")
						.filters(f->f.rewritePath("/product/(?<segment>.*)","/${segment}"))
						.uri("lb://productservice"))
				
				.build();
	}

}
