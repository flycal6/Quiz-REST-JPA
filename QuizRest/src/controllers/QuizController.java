package controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.QuizDAO;
import entities.Question;
import entities.Quiz;

@RestController()
@RequestMapping(value = "/quizzes")
public class QuizController {

	@Autowired
	private QuizDAO quizDao;

	@RequestMapping(path = "/ping", method = RequestMethod.GET)
	public String ping() {
		return "pong";
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<Quiz> index(HttpServletResponse res) {
		res.setStatus(202);
		return quizDao.index();
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Quiz show(@PathVariable int id, HttpServletResponse res) {
		res.setStatus(302);
		return quizDao.show(id);
	}

	@RequestMapping(path="/", method = RequestMethod.POST)
	public Quiz create(@RequestBody String quizJSON, HttpServletResponse res) {
		res.setStatus(201);
		return quizDao.create(quizJSON);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Quiz update(@PathVariable int id, @RequestBody String quizJSON, HttpServletResponse res) {
		res.setStatus(204);
		return quizDao.update(id, quizJSON);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public boolean destroy(@PathVariable int id, HttpServletResponse res) {
		res.setStatus(204);
		return quizDao.destroy(id);
	}

	@RequestMapping(path = "/{id}/questions", method = RequestMethod.GET)
	public Set<Question> showQuestions(@PathVariable int id, HttpServletResponse res) {
		res.setStatus(202);
		return quizDao.showQuestions(id);
	}

	@RequestMapping(path = "/{id}/questions", method = RequestMethod.POST)
	public Question createQuestions(@PathVariable int id, @RequestBody String questionJson, HttpServletResponse res) {
		res.setStatus(201);
		return quizDao.createQuestion(id, questionJson);
	}

	@RequestMapping(path = "/{id}/questions/{id}", method = RequestMethod.DELETE)
	public boolean destroyQuestions(@PathVariable int id, @PathVariable int questid, HttpServletResponse res) {
		res.setStatus(204);
		return quizDao.destroyQuestion(id, questid);
	}
}
