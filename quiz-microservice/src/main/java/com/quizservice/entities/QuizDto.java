package com.quizservice.entities;

public class QuizDto {

	private String category;
	private int noOfQuestion;
	private String quizTitle;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getNoOfQuestion() {
		return noOfQuestion;
	}
	public void setNoOfQuestion(int noOfQuestion) {
		this.noOfQuestion = noOfQuestion;
	}
	public String getQuizTitle() {
		return quizTitle;
	}
	public void setQuizTitle(String quizTitle) {
		this.quizTitle = quizTitle;
	}
	public QuizDto(String category, int noOfQuestion, String quizTitle) {
		super();
		this.category = category;
		this.noOfQuestion = noOfQuestion;
		this.quizTitle = quizTitle;
	}
	
	
	
}
