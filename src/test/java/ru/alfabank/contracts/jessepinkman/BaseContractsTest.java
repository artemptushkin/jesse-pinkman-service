package ru.alfabank.contracts.jessepinkman;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureStubRunner(
	ids = { "ru.alfabank.contracts:heisenberg-service:LATEST:8080" },
	consumerName = "jesse-pinkman",
	stubsMode = StubRunnerProperties.StubsMode.REMOTE,
	repositoryRoot = "git://git@github.com:artemptushkin/spring-cloud-contract-git-repo.git"
)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseContractsTest {

	@Autowired
	protected WebApplicationContext applicationContext;

	@Before
	public void setUp() {
		RestAssuredMockMvc.mockMvc(
			MockMvcBuilders
				.webAppContextSetup(applicationContext)
				.build()
		);
	}
}
