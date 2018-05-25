package com.campusnumerique.reseauSocial8;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {
	@Autowired
	private UserRepo userRepo;

	public ViewController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping ("/alluser")
	public String viewAllUser(Model model) {
		Iterable<User> userList = userRepo.findAll();
		model.addAttribute("userList", userList );
		return "allUserView";
	}


	@GetMapping ("/createuser")
	public String createUser(Model model) {
		model.addAttribute("user", new User() );
		return "userView";
	}

	@GetMapping ("/updateuser")
	public String updateUser(Model model, @RequestParam Long id) {
		Optional<User> userX = userRepo.findById(id);
		User user = userX.get();
		model.addAttribute("user", user );
		return "userView";
	}

	@GetMapping ("/deleteuser")
	public String deleteUser(Model model, @RequestParam Long id) {
		Optional<User> userX = userRepo.findById(id);
		User user = userX.get();
		userRepo.delete(user);
		return "redirect:/alluser";
	}

	
	@PostMapping("/saveuser")
	public String submitUser(@ModelAttribute User user) {
		userRepo.save(user);
		return "redirect:/alluser";
	}

	@GetMapping ("/friends")
	public String friends(Model model, @RequestParam Long id) {
		Optional<User> userX = userRepo.findById(id);
		User user = userX.get();
		model.addAttribute("user", user );
		return "friendsView";
	}


}
