package com.martini.main.controller;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@RestController
public class JwtController {

	@GetMapping("/generate-token")
	@ResponseBody
	public String generateToken() {

		// HEADER
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.ES256;
		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		// Creating the Header of
		HashMap<String, Object> header = new HashMap<String, Object>();
		header.put("alg", signatureAlgorithm.toString()); // HS256
		header.put("typ", "JWT");

		// CREACIÓN TOKEN Y AÑADIR HEADER
		JwtBuilder tokenJWT = Jwts.builder().setHeader(header).setId("1").setSubject("www.martini.cl")
				.claim("name", "Jorge Martini").claim("scope", "admins")
				.setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
				.setExpiration(Date.from(Instant.ofEpochSecond(4622470422L))).signWith(key);

		String tokenJWTString = tokenJWT.compact();
		System.out.println(tokenJWTString);

		return tokenJWTString;
	}
}
