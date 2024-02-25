package com.questionservice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.questionservice.entities.Question;
import com.questionservice.entities.QuestionWrapper;
import com.questionservice.entities.Response;
import com.questionservice.exceptions.ResourceNotFoundException;
import com.questionservice.repositories.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;
	
	public Question createQuestion(Question question) {
		Question save = questionRepository.save(question);
		return save;
	}
	
	public List<Question> getAllQuestions(){
		List<Question> list = questionRepository.findAll();
		return list;
	}
	
	public List<Question> getByCategory(String category){
		List<Question> byCategory = questionRepository.findAllByCategory(category);
		return byCategory;
	}
	
	public Question updateQuestion(Question question , int id) {
		Question savedQuestion = questionRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("User mila hi nahi"));
		savedQuestion.setOption1(question.getOption1());
		savedQuestion.setOption2(question.getOption2());
		savedQuestion.setOption3(question.getOption3());
		savedQuestion.setOption4(question.getOption4());
		savedQuestion.setQuestionTitle(question.getQuestionTitle());
		savedQuestion.setCategory(question.getCategory());
		savedQuestion.setDifficultyLevel(question.getDifficultyLevel());
		savedQuestion.setRightAnswer(question.getRightAnswer());
		
		Question save = questionRepository.save(savedQuestion);
		return save;
	}
	
	public void deleteQuestion(int id) {
		questionRepository.deleteById(id);
	}

	public List<Integer> generateQuestions(String qCategory, int qNumber) {
		List<Integer> randomQuestionByCategory = questionRepository.findRandomQuestionByCategory(qCategory, qNumber);
		return randomQuestionByCategory;
	}

	public List<QuestionWrapper> getQuestionsForQuiz(List<Integer> questions) {
		List<Question> questions2 = questionRepository.getQuestions(questions);
		List<QuestionWrapper> collect = questions2.stream().map(q -> {
			return new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
		}).collect(Collectors.toList());
		return collect;
	}

	public int calculateScore(List<Response> response) {
		int score = 0;
		for(Response resp : response) {
			if(resp.getResponse().equals(questionRepository.findById(resp.getId()).get().getRightAnswer()))
				score++;
		}
		return score;
	}
}
