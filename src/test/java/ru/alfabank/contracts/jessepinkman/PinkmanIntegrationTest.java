package ru.alfabank.contracts.jessepinkman;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;

@SpringBootTest
@AutoConfigureStubRunner(
	ids = { "ru.alfabank.contracts:heisenberg-service:+:2222" },
	consumerName = "jesse-pinkman",
	stubsPerConsumer = true,
	stubsMode = StubRunnerProperties.StubsMode.REMOTE,
	repositoryRoot = "git://git@github.com:artemptushkin/spring-cloud-contract-git-repo.git"
)
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class PinkmanIntegrationTest {

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

	@Test
	public void validate_should_sell_crystals_ok() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json;charset=UTF-8")
					.body("{\"amount\":200,\"price\":15000.0}");

		// when:
			ResponseOptions response = given().spec(request)
					.post("/shop/blue");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).isEqualTo("application/json;charset=UTF-8");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
		// and:
			assertThat(parsedJson.read("$.amount", Integer.class)).isEqualTo(200);
	}

}
