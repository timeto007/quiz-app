package com.microservices.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.quizapp.entity.Quiz;

public interface QuizDao  extends JpaRepository<Quiz, Integer>{

}
