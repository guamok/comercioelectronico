package com.comercioelectronico;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.is;

@SpringBootTest
class ComercioelectronicoApplicationTests {

	/**
	 * Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
	 * http://localhost:8080/ecommerce/price/2020-06-14:10:00:00/35455/1
	 */
	@Test
	void getDay14_10ProductBrandAndAppDateOK() {
		/** Price must be 35.50 */
		String responseOK = "{\"brandId\":1,\"appDate\":\"2020-06-14T10:00:00\",\"priceList\":1,\"productId\":35455,\"price\":35.50}";
		RestAssured.given()
				.when()
				.get("/ecommerce/price/2020-06-14:10:00:00/35455/1")
				.then()
				.statusCode(200).body(is(responseOK));
	}


	/**
	 * Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	 * http://localhost:8080/ecommerce/price/2020-06-14:16:00:00/35455/1
	 */
	@Test
	void getDay14_16ProductBrandAndAppDateOK() {
		/** Price must be 25.45 */
		String responseOK = "{\"brandId\":1,\"appDate\":\"2020-06-14T16:00:00\",\"priceList\":2,\"productId\":35455,\"price\":25.45}";
		RestAssured.given()
				.when()
				.get("/ecommerce/price/2020-06-14:16:00:00/35455/1")
				.then()
				.statusCode(200).body(is(responseOK));
	}


	/**
	 * Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	 * http://localhost:8080/ecommerce/price/2020-06-14:21:00:00/35455/1
	 */
	@Test
	void getDay14_21ProductBrandAndAppDateOK() {
		/** Price must be 35.50 */
		String responseOK = "{\"brandId\":1,\"appDate\":\"2020-06-14T21:00:00\",\"priceList\":1,\"productId\":35455,\"price\":35.50}";
		RestAssured.given()
				.when()
				.get("/ecommerce/price/2020-06-14:21:00:00/35455/1")
				.then()
				.statusCode(200).body(is(responseOK));
	}


	/**
	 * Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
	 */
	@Test
	void getDay15ProductBrandAndAppDateOK() {
		/** Price must be 30.50 */
		String responseOK = "{\"brandId\":1,\"appDate\":\"2020-06-15T10:00:00\",\"priceList\":3,\"productId\":35455,\"price\":30.50}";
		RestAssured.given()
				.when()
				.get("/ecommerce/price/2020-06-15:10:00:00/35455/1")
				.then()
				.statusCode(200).body(is(responseOK));
	}

	/**
	 *  Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
	 * http://localhost:8080/ecommerce/price/2020-06-14:10:00:00/35455/1
	 */
	@Test
	void getDay16_21ProductBrandAndAppDateOK() {
		/** Price must be 38.95 */
		String responseOK = "{\"brandId\":1,\"appDate\":\"2020-06-16T21:00:00\",\"priceList\":4,\"productId\":35455,\"price\":38.95}";
		RestAssured.given()
				.when()
				.get("/ecommerce/price/2020-06-16:21:00:00/35455/1")
				.then()
				.statusCode(200).body(is(responseOK));
	}

	/**
	 * Typed Wrong request. appDate(year wrong)
	 * http://localhost:8080/ecommerce/price/2020-06-14:10:00:00/35455/1
	 */
	@Test
	void getDay14_10_220ProductBrandAndAppDateNotOK() {

		RestAssured.given()
				.when()
				.get("/ecommerce/price/220-6-14:10:00:00/35455/1")
				.then()
				.statusCode(422);
	}

	/**
	 * Typed OK request, BUt , there isn't  price to 2027
	 * http://localhost:8080/ecommerce/price/2020-06-14:10:00:00/35455/1
	 */
	@Test
	void getDay14_10_2027ProductBrandAndAppDateOK() {

		RestAssured.given()
				.when()
				.get("/ecommerce/price/2027-06-14:10:00:00/35455/1")
				.then()
				.statusCode(404);
	}
}
