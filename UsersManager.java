package klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klu.repository.UsersRepository;

@Service
public class UsersManager 

{   @Autowired
	EmailManager EM;
	@Autowired
	UsersRepository UR;
	@Autowired
	JWTManager jwt;
	public String addUSer(Users U) 
	{
		if(UR.validateEmail(U.getEmail())>0)
			return "401::email id exist";
		
		UR.save(U);
		return "200::Registration done successfully";
	}
	public String recoverPassword(String email) {
		Users U=UR.findById(email).get();
		 String message=String.format("dear %s,\n\n\n pass word is: %s",U.getFullname(),U.getPassword());
		return EM.sendMail(U.getEmail(),"JobPortal recover password", message);
	}
	public String validateCredentials(String email,String password) 
	{
		if(UR.validateCredentials(email, password)>0) 
		{
			String token=jwt.generateToken(email);
			return "200::"+token;
		}
		return "401:: invalid credentials";
	}
	public String getFullname(String token) {
		String email=jwt.validateToken(token);
		if(email.equals("401")) 
		{
			return "401::Token expired";
		}else 
		{
			Users u=UR.findById(email).get();
			return u.getFullname();
		}
		
	}
}

