package data;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Question createQuestion(int id, String quizJson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean destroyQuestion(int id, int questid) {
		// TODO Auto-generated method stub
		return false;
	}

}
