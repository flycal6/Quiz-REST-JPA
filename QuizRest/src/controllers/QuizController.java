package controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.QuizDAO;
import entities.Quiz;

@RestController()
@RequestMapping(value="/quizzes")
public class QuizController {

	@Autowired
	private QuizDAO quizDao;

	@RequestMapping(path = "/ping", method = RequestMethod.GET)
	public String ping() {
		return "pong";
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<Quiz> index(HttpServletResponse res){
		return quizDao.index();
	}
}
