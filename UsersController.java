package klu.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import klu.model.Users;
import klu.model.UsersManager;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173/")
public class UsersController 
{
	@Autowired
	UsersManager UM;
	@PostMapping("/signup")
	public String signup(@RequestBody Users U)
	{
		return UM.addUSer(U);
		
	}
	@GetMapping("/forgetpassword/{email}")
	public String forgetpassword(@PathVariable("email") String emailid) 
	{
		return UM.recoverPassword(emailid);	
	}
	@PostMapping("/signin")
	public String signin(@RequestBody Users U) 
	{
		return UM.validateCredentials(U.getEmail(), U.getPassword());
		
	}
	@PostMapping("/getname")
	public String getname(@RequestBody Map<String,String> data) {
		return UM.getFullname(data.get("crsid"));
	}
	

}
