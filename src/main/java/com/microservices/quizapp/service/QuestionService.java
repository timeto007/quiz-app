package com.microservices.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.quizapp.dao.QuestionDao;
import com.microservices.quizapp.entity.Questions;

@Service
public class QuestionService {
	@Autowired
	QuestionDao questionDao;

	
	public ResponseEntity<List<Questions>> allQuestions(){
		
		try {
			return  new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			//System.out.println("error occurred "+e);
		}return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
		
	}
	
	
	public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
		try {
			return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}return new ResponseEntity<List<Questions>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
	}

	public ResponseEntity<String> addQuestion(Questions question) {
//		Questions filteredQuestion = removeNullValues(question);
		
		try {
			questionDao.save(question);
			return new ResponseEntity<>("Successfully added the question",HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
		}return new ResponseEntity<>("Bad Request ",HttpStatus.BAD_REQUEST);
		
	}
	
//	private Questions removeNullValues(Questions question) {
//	    Optional<Questions> optionalQuestion = Optional.ofNullable(question)
//	        .map(q -> {
//	            if (q.getDifficultyLevel() == null) q.setDifficultyLevel("");
//	            if (q.getCategory() == null) q.setCategory("");
//	            return q;
//	        });
//
//	    return optionalQuestion.orElse(new Questions());
//	}






}
