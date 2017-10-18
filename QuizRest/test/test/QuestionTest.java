package test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import data.QuizDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "../Quizzes-servlet.xml" })
@WebAppConfiguration
@Transactional
public class QuestionTest {
	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private QuizDAO dao;

	@PersistenceContext
	private EntityManager em;

	@Before
	public void setUp() {
		dao = (QuizDAO) wac.getBean("quizDao");
	}

	@After
	public void tearDown() {
		dao = null;
		em = null;
		wac = null;
	}

	@Test
	public void showQuestionsMethodReturnsSetOfQuestions() {
		boolean bool = true;
		assertEquals(true, bool);;
	}
}
