package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Question;
import entities.Quiz;

@Transactional
@Repository
public class QuizDAOImpl implements QuizDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Quiz> index() {
		String q = "SELECT q FROM Quiz q";
		return em.createQuery(q, Quiz.class).getResultList();
	}

	@Override
	public Quiz show(int id) {
		return em.find(Quiz.class, id);
	}

	@Override
	public Quiz create(String quizJSON) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Quiz quizMapped = mapper.readValue(quizJSON, Quiz.class);
			em.persist(quizMapped);
			em.flush();
			return quizMapped;
		} catch (Exception e) {
			e.printStackTrace();		
		}
		return null;
	}

	@Override
	public Quiz update(int id, String quizJSON) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Quiz quizToUpdate = em.find(Quiz.class, id);
			Quiz mappedQuiz = mapper.readValue(quizJSON, Quiz.class);
			
			quizToUpdate.setName(mappedQuiz.getName());
			return quizToUpdate;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean destroy(int id) {
		try {
			em.remove(em.find(Quiz.class, id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Set<Question> showQuestions(int id) {
		// This MySQL query:
//		String q = "SELECT * FROM Question q JOIN Answer a ON q.id = a.question_id WHERE q.quiz_id=10;";
		
		// Translates in Hibernate to this:
		String q = "SELECT q FROM Question q JOIN FETCH q.answers WHERE q.quiz.id=:id";
		return new HashSet<Question>(em.createQuery(q, Question.class).setParameter("id", id).getResultList());
	}

	@Override
	public Question createQuestion(int id, String quizJson) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Question questionMapped = mapper.readValue(quizJson, Question.class);
			questionMapped.setQuiz(em.find(Quiz.class, id));
			em.persist(questionMapped);
			em.flush();
			return questionMapped;
		} catch (Exception e) {
			e.printStackTrace();		
		}
		return null;
	}

	@Override
	public boolean destroyQuestion(int id, int questid) {
		try {
			em.remove(em.find(Question.class, questid));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
