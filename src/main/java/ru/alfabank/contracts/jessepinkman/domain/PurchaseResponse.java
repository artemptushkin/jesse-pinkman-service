package ru.alfabank.contracts.jessepinkman.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class PurchaseResponse {
	@NotNull
	private Integer amount;
}
