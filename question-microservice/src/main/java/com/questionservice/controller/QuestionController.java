package com.questionservice.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.questionservice.entities.Question;
import com.questionservice.entities.QuestionWrapper;
import com.questionservice.entities.Response;
import com.questionservice.services.QuestionService;


@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@PostMapping("/add")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question){
		Question question2 = questionService.createQuestion(question);
		return new ResponseEntity<>(question2 , HttpStatus.CREATED);
	}
	
	@GetMapping("/allquestions")
	public ResponseEntity<List<Question>> getAllQuestion(){
		List<Question> allQuestions = questionService.getAllQuestions();
		return new ResponseEntity<>(allQuestions , HttpStatus.OK);
	}
	
	@GetMapping("/allquestions/{category}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
		List<Question> byCategory = questionService.getByCategory(category);
		return new ResponseEntity<>(byCategory , HttpStatus.OK);
	}
	
	@PostMapping("/updateQuestion/{id}")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question , @PathVariable int id){
		Question question2 = questionService.updateQuestion(question , id);
		return new ResponseEntity<>(question2 , HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteQuestion/{id}")
	public ResponseEntity<HttpStatus> updateQuestion(@PathVariable int id){
		questionService.deleteQuestion(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// create questions for quiz
	@GetMapping("/generate")
	public ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestParam String qCategory , @RequestParam int qNumber) {
		List<Integer> questions = questionService.generateQuestions(qCategory , qNumber);
		return new ResponseEntity<>(questions , HttpStatus.OK);
	}
	
	// get all the questions according to quiz id
	
	@PostMapping("/quiz-questions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsOfQuiz(@RequestBody List<Integer> questions) {
		List<QuestionWrapper> questionsForQuiz = questionService.getQuestionsForQuiz(questions);
		return new ResponseEntity<List<QuestionWrapper>>(questionsForQuiz , HttpStatus.OK);
	}
	
	
	// calculate score for quiz according to quiz id
	@PostMapping("/score")
	public ResponseEntity<Integer> getQuizScore(@RequestBody List<Response> response) {
		int calculateScore = questionService.calculateScore(response);
		return new ResponseEntity<Integer>(calculateScore , HttpStatus.OK);
	}
	
	
	

}
