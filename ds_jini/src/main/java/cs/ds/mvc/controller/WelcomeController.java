package cs.ds.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Default welcome page of the application
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

	@RequestMapping
	public String welcome() {
        return "welcome";
	}
}
