package ru.alfabank.contracts.jessepinkman.domain;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AmountResponse {
	@NotNull
	private Integer amount;
	@NotNull
	private BigDecimal price;
}
