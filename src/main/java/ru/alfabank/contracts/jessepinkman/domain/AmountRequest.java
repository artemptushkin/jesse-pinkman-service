package ru.alfabank.contracts.jessepinkman.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AmountRequest {
	private Integer quantity;
}
