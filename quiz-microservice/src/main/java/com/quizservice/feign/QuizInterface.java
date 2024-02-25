package com.quizservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.quizservice.entities.QuestionWrapper;
import com.quizservice.entities.Response;


@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

	@GetMapping("/question/generate")
	public ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestParam String qCategory , @RequestParam int qNumber); 
	
	
	@PostMapping("/question/quiz-questions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsOfQuiz(@RequestBody List<Integer> questions); 
	
	
	@PostMapping("/question/score")
	public ResponseEntity<Integer> getQuizScore(@RequestBody List<Response> response);
}
