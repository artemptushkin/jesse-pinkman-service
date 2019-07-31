package ru.alfabank.contracts.jessepinkman;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import org.junit.Test;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;

public class PinkmanIntegrationTest extends BaseContractsTest {

	@Test
	public void validate_should_sell_crystals_ok() throws Exception {
		// given:
			MockMvcRequestSpecification request = given()
					.header("Content-Type", "application/json;charset=UTF-8")
					.body("{\"amount\":200,\"price\":15000.0}");

		// when:
			ResponseOptions response = given().spec(request)
					.post("/crystals/blue/buy");

		// then:
			assertThat(response.statusCode()).isEqualTo(200);
			assertThat(response.header("Content-Type")).isEqualTo("application/json;charset=UTF-8");
		// and:
			DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
		// and:
			assertThat(parsedJson.read("$.amount", Integer.class)).isEqualTo(200);
	}

}
