package org.pjay.codes;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

/**
 * @author vijayk
 *
 */
// https://nickolasfisher.com/blog/How-to-Make-Concurrent-Service-API-Calls-in-Java-Using-Spring-Boot
// https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html
// https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html#allOf-java.util.concurrent.CompletableFuture...-
// https://medium.com/sipios/how-to-make-parallel-calls-in-java-springboot-application-and-how-to-test-them-dcc27318a0cf#:~:text=So%20you%20want%20to%20parallelize,CompletableFuture%3E
// https://www.codepedia.org/ama/how-to-make-parallel-calls-in-java-with-completablefuture-example
// https://www.baeldung.com/spring-webclient-simultaneous-calls
@SpringBootApplication
@EnableAsync
public class ConcurrentExternalServiceCallerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConcurrentExternalServiceCallerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/concurrent/ThreadPoolTaskExecutor.html#setQueueCapacity-int-
	// https://www.baeldung.com/java-threadpooltaskexecutor-core-vs-max-poolsize
	@Bean
	public Executor executor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(4);
		threadPoolTaskExecutor.setMaxPoolSize(4);
		// 100 is more than enough for testing this application
		threadPoolTaskExecutor.setQueueCapacity(100);
		threadPoolTaskExecutor.initialize();
		return threadPoolTaskExecutor;
	}

}
