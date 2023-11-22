package com.comercioelectronico;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.is;

@SpringBootTest
class ComercioelectronicoApplicationTests {

	/**
	 * Initializes
	 */
	@BeforeAll
	static void setUp() {
	}

	/**
	 * Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	 * http://localhost:8080/ecommerce/price/2020-06-14:10:00:00/35455/1
	 */
	@Test
	void getDay14_10ProductBrandAndAppDateOK() {

		String response = "{\"brandId\":1,\"appDate\":\"2020-06-14T10:00:00\",\"priceList\":1,\"productId\":35455,\"price\":35.50}";
		RestAssured.given()
				.when()
				.get("/ecommerce/price/2020-06-14:10:00:00/35455/1")
				.then()
				.statusCode(200).body(is(response));
	}


	/**
	 * Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	 * http://localhost:8080/ecommerce/price/2020-06-14:16:00:00/35455/1
	 */
	@Test
	void getDay14_16ProductBrandAndAppDateOK() {

		String response = "{\"brandId\":1,\"appDate\":\"2020-06-14T16:00:00\",\"priceList\":2,\"productId\":35455,\"price\":25.45}";
		RestAssured.given()
				.when()
				.get("/ecommerce/price/2020-06-14:16:00:00/35455/1")
				.then()
				.statusCode(200).body(is(response));
	}


	/**
	 * Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	 * http://localhost:8080/ecommerce/price/2020-06-14:21:00:00/35455/1
	 */
	@Test
	void getDay14_21ProductBrandAndAppDateOK() {

		String response = "{\"brandId\":1,\"appDate\":\"2020-06-14T21:00:00\",\"priceList\":1,\"productId\":35455,\"price\":35.50}";
		RestAssured.given()
				.when()
				.get("/ecommerce/price/2020-06-14:21:00:00/35455/1")
				.then()
				.statusCode(200).body(is(response));
	}


	/**
	 * Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
	 */
	@Test
	void getDay15ProductBrandAndAppDateOK() {

		//http://localhost:8080/ecommerce/price/2020-06-15:10:00:00/35455/1

		String response = "{\"brandId\":1,\"appDate\":\"2020-06-15T10:00:00\",\"priceList\":3,\"productId\":35455,\"price\":30.50}";
		RestAssured.given()
				.when()
				.get("/ecommerce/price/2020-06-15:10:00:00/35455/1")
				.then()
				.statusCode(200).body(is(response));
	}

	/**
	 *  Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
	 * http://localhost:8080/ecommerce/price/2020-06-14:10:00:00/35455/1
	 */
	@Test
	void getDay16_21ProductBrandAndAppDateOK() {

		String response = "{\"brandId\":1,\"appDate\":\"2020-06-16T21:00:00\",\"priceList\":4,\"productId\":35455,\"price\":38.95}";
		RestAssured.given()
				.when()
				.get("/ecommerce/price/2020-06-16:21:00:00/35455/1")
				.then()
				.statusCode(200).body(is(response));
	}

	/**
	 *  Wrong request.
	 * http://localhost:8080/ecommerce/price/2020-06-14:10:00:00/35455/1
	 */
	@Test
	void getDay14_10ProductBrandAndAppDateNotOK() {

		RestAssured.given()
				.when()
				.get("/ecommerce/price/2020-6-14:10:00:00/35455/1")
				.then()
				.statusCode(500);
	}
}
