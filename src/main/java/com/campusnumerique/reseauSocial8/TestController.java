package com.campusnumerique.reseauSocial8;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@Autowired
	private UserRepo userRepo;

	public TestController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping ("/hello")
	public String hello() {
		return "xxx";
	}

	@GetMapping ("/newUser")
	public User newUser(@RequestParam String firstName, @RequestParam String lastName) {
		User userX = new User();
		userX.setFirstName(firstName);
		userX.setLastName(lastName);
		userRepo.save(userX);
		return userX;
	}
	
	@GetMapping ("/all")
	public Iterable<User> allUsers() {
		return userRepo.findAll();
	}

	@GetMapping ("/find")
	public Optional<User> findUser(@RequestParam Long id) {
		Optional<User> userX = userRepo.findById(id);
		return userX;
	}
	
	@PostMapping ("/create")
	public User createUser(@RequestBody User user) {
		userRepo.save(user);
		return user;
	}

	@GetMapping ("/delete")
	public String deleteUser(@RequestParam Long id) {
		userRepo.deleteById(id);
		return "user " + id + " supprimé";
	}
	
	@GetMapping ("/update")
	public String updateUser(@RequestParam Long id, @RequestParam String firstName) {
		Optional<User> userX = userRepo.findById(id);
		if (userX.isPresent()) {
			User user = userX.get();
			user.setFirstName(firstName);
			userRepo.save(user);
			return "Update user " + user.getId() + " " + user.getFirstName() + " " + user.getLastName();
		} else { 
			return "Unknown user " + id;
		}
	}
}
