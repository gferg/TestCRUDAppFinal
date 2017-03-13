package app.controller;

import app.domain.User;
import app.domain.UserStatus;
import app.repository.UserRepository;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/")
public class MainController {
	@Autowired
	private UserRepository userRepository;

	
	@GetMapping(path="/add")
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String age, @RequestParam String status) {

		UserStatus userStatus;
		if(status.equals("TRUE")) userStatus = UserStatus.TRUE;
		else userStatus = UserStatus.FALSE;

		User user = new User(name, age, userStatus);
		userRepository.save(user);
		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	//pagination
	final UserService userService;

	@Autowired
	public MainController( UserService userService ){
		this.userService = userService;
	}

	@RequestMapping(value="/",method= RequestMethod.GET)
	Page<User> list(Pageable pageable){
		Page<User> persons = userService.listAllByPage(pageable);
		return persons;
	}

}
