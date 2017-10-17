package data;

import java.util.List;
import java.util.Set;

import entities.Question;
import entities.Quiz;

public interface QuizDAO {
	public List<Quiz> index();
	public Quiz show(int id);
	public Quiz create(String quizJSON);
	public Quiz update(int id, String quizJSON);
	public boolean destroy(int id);
	public Set<Question> showQuestions(int id);
	public Question createQuestion(int id, Question q);
	public boolean destroyQuestion(int id, int questid);
}
