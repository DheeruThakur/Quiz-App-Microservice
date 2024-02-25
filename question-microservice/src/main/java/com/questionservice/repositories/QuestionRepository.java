package com.questionservice.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.questionservice.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	List<Question> findAllByCategory(String category);
	
//	@Query(value = "SELECT * FROM question q WHERE q.category =:cat ORDER BY RANDOM() LIMIT :qno" , nativeQuery = true)
	@Query("SELECT q.id FROM Question q WHERE q.category = :cat ORDER BY FUNCTION('RAND') LIMIT :qno")
	List<Integer> findRandomQuestionByCategory(String cat, int qno);

	@Query("SELECT q FROM Question q WHERE q.id IN :questions")
	List<Question> getQuestions(List<Integer> questions);

}
