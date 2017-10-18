package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="answer_text")
	private String answerText;
	
	@Column(name="is_correct")
	private Boolean isCorrect;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="question_id")
	private Question question;

	/****************** Gets and Sets *******************************/
	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Answer [id=");
		builder.append(id);
		builder.append(", answerText=");
		builder.append(answerText);
		builder.append(", isCorrect=");
		builder.append(isCorrect);
		builder.append("]");
		return builder.toString();
	}
	
	

}
