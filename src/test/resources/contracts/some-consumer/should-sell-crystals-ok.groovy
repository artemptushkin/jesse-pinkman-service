import org.springframework.cloud.contract.spec.Contract

Contract.make {
	request {
		method 'POST'
		url '/crystals/blue/buy'
		headers {
			header 'Content-Type' : 'application/json;charset=UTF-8'
		}
		body(
			amount: 200
		)
	}
	response {
		status 200
		headers {
			header 'Content-Type' : 'application/json;charset=UTF-8'
		}
		body(
			price: 15000d
		)
		bodyMatchers {
			jsonPath('$.price', byEquality())
		}
	}
}
