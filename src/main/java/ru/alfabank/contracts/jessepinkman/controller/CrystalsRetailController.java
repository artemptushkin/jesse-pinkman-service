package ru.alfabank.contracts.jessepinkman.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.alfabank.contracts.jessepinkman.domain.AmountRequest;
import ru.alfabank.contracts.jessepinkman.domain.AmountResponse;
import ru.alfabank.contracts.jessepinkman.domain.PurchaseRequest;
import ru.alfabank.contracts.jessepinkman.domain.PurchaseResponse;

@RequestMapping("/crystals")
@RestController
public class CrystalsRetailController {

	private final RestTemplate restTemplate;
	private static final Double EXPECTED_PRICE = 50.0;
	private static final Double MARGIN = 1.5;

	public CrystalsRetailController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@RequestMapping("/blue/buy")
	public PurchaseResponse buyBlue(@RequestBody PurchaseRequest purchaseRequest) {
		Double requestedAmount = purchaseRequest.getMoney() / ( EXPECTED_PRICE * MARGIN );
		AmountRequest amountRequest = new AmountRequest().setAmount(requestedAmount.intValue());

		ResponseEntity<AmountResponse> responseEntity = restTemplate.postForEntity(
			"/blueCrystals/create", amountRequest, AmountResponse.class);

		if (responseEntity.getBody() == null) throw new IllegalStateException("Where is my crystals, b$tch");

		return new PurchaseResponse().setAmount(responseEntity.getBody().getAmount());
	}
}
