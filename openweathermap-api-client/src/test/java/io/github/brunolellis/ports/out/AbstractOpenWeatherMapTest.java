package io.github.brunolellis.ports.out;

import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortsTestConfig.class)
@TestPropertySource("classpath:application.properties")
public abstract class AbstractOpenWeatherMapTest {

}

@Configuration
@ComponentScan("io.github.brunolellis.ports")
class PortsTestConfig {
	
	@EnableConfigurationProperties//(OpenWeatherMapProperties.class)
	public static class TestConfiguration {

	}

}