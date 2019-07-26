package ru.alfabank.contracts.jessepinkman.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Price has changed, b$tch")
public class PriceHasChangedException extends RuntimeException {
	public PriceHasChangedException(String message) {
		super(message);
	}
}
