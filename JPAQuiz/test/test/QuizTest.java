package test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Quiz;

public class QuizTest {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	private Quiz q = null;

	@Before
	public void setup() throws Exception {
		emf = Persistence.createEntityManagerFactory("QuizApp");
		em = emf.createEntityManager();
		q = em.find(Quiz.class, 1);
	}

	@After
	public void teardown() {
		q = null;
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
		assertEquals("Dinosaurs", q.getName());
	}

}
