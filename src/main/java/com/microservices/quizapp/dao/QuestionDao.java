package com.microservices.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.microservices.quizapp.entity.Questions;

public interface QuestionDao extends JpaRepository<Questions, Integer>  {
	
	public List<Questions> findByCategory(String category);
	
	@Query(value="SELECT * FROM QUESTIONS q WHERE q.CATEGORY=:category ORDER BY RANDOM() LIMIT :nomberOfQuestion",nativeQuery =true )
	public List<Questions> findRandomQuestionsByCategory(String category, int nomberOfQuestion);
}
