package com.quizservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizservice.entities.Question;
import com.quizservice.entities.Quiz;
import com.quizservice.entities.QuestionWrapper;
import com.quizservice.entities.Response;
import com.quizservice.exceptions.ResourceNotFoundException;
import com.quizservice.feign.QuizInterface;
import com.quizservice.repositories.QuizRepository;

@Service
public class QuizService {
	
	@Autowired
	QuizRepository quizRepository;
	
	@Autowired
	QuizInterface quizInterface;
	
//	@Autowired
//	QuestionRepository questionRepository;

	public Quiz createQuiz(String cat , int qno , String title) {
		Quiz quiz = new Quiz();
		List<Integer> questions = quizInterface.generateQuestionsForQuiz(cat, qno).getBody();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		Quiz save = quizRepository.save(quiz);
		return save;
	}
	
	public List<QuestionWrapper> fetchQuiz(int id) {
		Quiz quiz = quizRepository.findById(id).get();
		List<QuestionWrapper> body = quizInterface.getQuestionsOfQuiz(quiz.getQuestions()).getBody();
		return body;
	}

	public int generateResult(List<Response> response) {
		return quizInterface.getQuizScore(response).getBody();
	}
}
