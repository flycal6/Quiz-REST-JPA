package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

}
