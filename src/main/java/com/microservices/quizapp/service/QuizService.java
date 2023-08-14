package com.microservices.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.quizapp.dao.QuestionDao;
import com.microservices.quizapp.dao.QuizDao;
import com.microservices.quizapp.entity.QuestionWrapper;
import com.microservices.quizapp.entity.Questions;
import com.microservices.quizapp.entity.Quiz;
import com.microservices.quizapp.entity.Response;

@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;
	@Autowired
	QuestionDao questionDao;
	
	public ResponseEntity<String> createQuiz(String category, int nomberOfQuestion, String quizName) {
		
		List<Questions> questions=questionDao.findRandomQuestionsByCategory(category,nomberOfQuestion);
		
		Quiz q=new Quiz(quizName);
		q.setQuestions(questions);
		quizDao.save(q);
		return new ResponseEntity<String>("created",HttpStatus.CREATED);
		
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
		
		Optional<Quiz> quiz=quizDao.findById(id);
		List<Questions> questions=quiz.get().getQuestions();
		List<QuestionWrapper> questionForUser=new ArrayList<>();
		
		for(Questions q:questions) {
			QuestionWrapper qw=new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			questionForUser.add(qw);
		}
		return new ResponseEntity<List<QuestionWrapper>>(questionForUser,HttpStatus.OK);
		
	
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
	    Quiz quiz = quizDao.findById(id).orElse(null);
	    
	    if (quiz == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	    List<Questions> questions = quiz.getQuestions();

	    int correctCount = 0;

	    for (Response response : responses) {
	        for (Questions question : questions) {
	            if (response.getId().equals(question.getId()) && response.getResponse().equals(question.getRightAnswer())) {
	                correctCount++;
	                break; // Move to the next response once a match is found
	            }
	        }
	    }

	    return new ResponseEntity<>(correctCount, HttpStatus.OK);
	}

		
	

}
