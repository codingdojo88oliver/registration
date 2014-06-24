package application.controllers;

import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import application.models.User;
import application.repositories.UserRepository;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Autowired private UserRepository userRepository;

	private static User user;
	
	@RequestMapping("/new")
	public String newUser(Model model) {
		user = new User();
		model.addAttribute("user", user);
		model.addAttribute("users", userRepository.findAll());
		
		return "new";
	}
	
	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public String createUser(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest req) {

		if(bindingResult.hasErrors()) {
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				redirectAttributes.addFlashAttribute(error.getField(), error.getDefaultMessage());
			}
			
			return "redirect:/users/new";
		} 
		else if(userRepository.findByEmail(req.getParameter("email")) != null) {
			redirectAttributes.addFlashAttribute("email", "Email already exists!");
			return "redirect:/users/new";
		}			
		
		//prepare the timestamp
		Date date = new Date(System.currentTimeMillis());
		Timestamp timestamp = new Timestamp(date.getTime());
		
		user.setCreated_at(timestamp);
		user.setUpdated_at(timestamp);
		
		userRepository.save(user);
				
		return "redirect:/users/new";
	}
}
