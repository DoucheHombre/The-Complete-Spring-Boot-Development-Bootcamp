package com.ltp.gradesubmission;

public class Grade {

	private String name;
	private String subject;
	private String score;

	public Grade() {

	}

	public Grade(String name, String subject, String score) {
		super();
		this.name = name;
		this.subject = subject;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Grade [name=" + name + ", subject=" + subject + ", score=" + score + "]";
	}

}
