import org.springframework.cloud.contract.spec.Contract

Contract.make {
	request {
		method 'POST'
		url '/crystals/blue/buy'
		headers {
			header 'Content-Type' : 'application/json;charset=UTF-8'
		}
		body(
			money: 3000.0d
		)
	}
	response {
		status 200
		headers {
			header 'Content-Type' : 'application/json;charset=UTF-8'
		}
		body(
			amount: 40
		)
		bodyMatchers {
			jsonPath('$.amount', byRegex('^\\d*$'))
		}
	}
}
