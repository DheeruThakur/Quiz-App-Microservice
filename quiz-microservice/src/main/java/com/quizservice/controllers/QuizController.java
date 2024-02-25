package com.quizservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.quizservice.entities.Quiz;
import com.quizservice.entities.QuizDto;
import com.quizservice.entities.QuestionWrapper;
import com.quizservice.entities.Response;
import com.quizservice.services.QuizService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("/create")
	public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDto quizDto) {
		Quiz quiz = quizService.createQuiz(quizDto.getCategory(), quizDto.getNoOfQuestion(), quizDto.getQuizTitle());
		return new ResponseEntity<Quiz>(quiz , HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id) {
		List<QuestionWrapper> fetchQuiz = quizService.fetchQuiz(id);
		return new ResponseEntity<>(fetchQuiz , HttpStatus.CREATED);
	}
	
	@PostMapping("/result")
	public ResponseEntity<Integer> createResult(@RequestBody List<Response> response) {
		int result = quizService.generateResult(response);
		return new ResponseEntity<Integer>(result , HttpStatus.OK);
	}
	

}
