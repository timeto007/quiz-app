package com.microservices.quizapp.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Data
public class Response {

	private Integer id;
	private String response;
	
}
