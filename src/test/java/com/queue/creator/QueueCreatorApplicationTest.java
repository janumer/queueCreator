package com.queue.creator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

@SpringBootTest
class QueueCreatorApplicationTest {

	@Test
	void contextLoads() {
	}

	@Test
	void mainTest() {
		try (var mockedStatic = mockStatic(SpringApplication.class)) {
			mockedStatic.when(() -> SpringApplication.run(QueueCreatorApplication.class, new String[]{})).thenReturn(mock(ConfigurableApplicationContext.class));

			QueueCreatorApplication.main(new String[]{});

			mockedStatic.verify(() -> SpringApplication.run(QueueCreatorApplication.class, new String[]{}));
		}
	}
}
