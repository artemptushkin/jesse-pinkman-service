package ru.alfabank.contracts.jessepinkman.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.alfabank.contracts.jessepinkman.domain.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/shop")
public class CrystalsRetailController {

	private final RestTemplate restTemplate;
	private static final BigDecimal MARGIN = BigDecimal.valueOf(1.5);

	public CrystalsRetailController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@PostMapping("/blue")
	public PurchaseResponse buyBlue(@RequestBody PurchaseRequest purchaseRequest) {
		AmountRequest amountRequest = new AmountRequest().setQuantity(purchaseRequest.getAmount());

		ResponseEntity<AmountResponse> responseEntity = restTemplate.postForEntity(
			"/blueCrystals", amountRequest, AmountResponse.class);

		if (responseEntity.getBody() == null) throw new IllegalStateException("Where is my crystals, b$tch");

		Double fetchedPrice = responseEntity.getBody().getPrice().multiply(MARGIN).doubleValue();

		if (fetchedPrice.compareTo(purchaseRequest.getPrice()) != 0) throw new PriceHasChangedException("Price has changed, b$tch");

		return new PurchaseResponse().setAmount(
			responseEntity.getBody()
				.getAmount()
		);
	}
}
