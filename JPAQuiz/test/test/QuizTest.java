package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Answer;
import entities.Question;
import entities.Quiz;


public class QuizTest {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	private Quiz quiz = null;
	
	
	

	@Before
	public void setup() throws Exception {
		emf = Persistence.createEntityManagerFactory("QuizApp");
		em = emf.createEntityManager();
		quiz = em.find(Quiz.class, 1);
	}

	@After
	public void teardown() {
		quiz = null;
		em.close();
		emf.close();
	}

	@Test
	public void test() {
		boolean bool = true;
		assertEquals(true, bool);
	}
	
	@Test
	public void databaseConnectsAndFindsFirstEntry() {
		assertEquals("Dinosaurs", quiz.getName());
	}
	
	@Test
	public void getListOfQuestionsFromATest() {
		quiz = em.find(Quiz.class, 10);
		assertEquals(5, quiz.getQuestions().size());
	}
	
	@Test
	public void getListOfAnswersFromQuestion() {
		String q = "SELECT q FROM Question q JOIN FETCH q.answers WHERE q.id=1";
		int numOfQuestions = em.createQuery(q, Question.class).getResultList().size();
		assertEquals(4, numOfQuestions);
	}
	
	@Test
	public void getAnswerTextFromAQuestion() {
		String query = "SELECT q FROM Question q JOIN FETCH q.answers WHERE q.id=1";
		Question q = em.createQuery(query, Question.class).getResultList().get(0);
		boolean answerFound = false;
		for ( Answer a : q.getAnswers()) {
			if(a.getAnswerText().equals("Rhode Island")) {
				answerFound = true;
				break;
			}
		}
		assertTrue(answerFound);
	}
	
	@Test
	public void getQuestionTextFromAQuiz() {
		quiz = em.find(Quiz.class, 10);
		
		boolean questionTextFound = false;
		for (Question q : quiz.getQuestions()) {
			if(q.getQuestionText().equals("What is the Colorado state flower?")) {
				questionTextFound = true;
				break;
			}
		}
		assertTrue(questionTextFound);
	}

}
