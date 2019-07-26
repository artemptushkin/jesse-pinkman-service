package ru.alfabank.contracts.jessepinkman.domain;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class PurchaseRequest {
	@NotNull
	private Integer amount;
	@NotNull
	private Double price;
}
