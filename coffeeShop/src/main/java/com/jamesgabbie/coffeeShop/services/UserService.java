package com.jamesgabbie.coffeeShop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamesgabbie.coffeeShop.models.Item;
import com.jamesgabbie.coffeeShop.models.User;
import com.jamesgabbie.coffeeShop.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository uRepo;
	
	// Find user by UserName
    public User findByEmail(String email) {
        return uRepo.findByEmail(email);
    }
    // Find User
 	public User findUser(Long id) {
 		Optional<User> optionalUser = uRepo.findById(id);
 		if(optionalUser.isPresent()) {
 			return optionalUser.get();
 		} else {
 			return null;
 		}
 	}
 	
 	// Create User
 	public User createUser(User user) {
 		return uRepo.save(user);
 	}
 	
 	//Hash Password
 	public String hashPass(String pw) {
	    String hashedPw = BCrypt.hashpw(pw, BCrypt.gensalt());	    
	    return hashedPw;
	}
 	
 	// Authenticate USER
    public boolean authenticateUser(String email, String password) {
        User user = uRepo.findByEmail(email);
        if(user == null) {
            return false;
        } else {
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    //Update User
    public User updateUser(Long id) {
    	User user;
    	Optional<User> optionalUser = uRepo.findById(id);
 		if(optionalUser.isPresent()) {
 			user =  optionalUser.get();
 		} else {
 			return null;
 		}
    	return uRepo.save(user);
    }
	
// Set Default Content
	//	TEXT CONTENT
	public String getDefaultText(String type) {
		String text = "";
		if(type.equals("welcome") == true ) {
			text = "welcome: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Amet, cras sed sapien pretium elementum. Quam nisl vulputate mauris nam ac ullamcorper arcu. Et sed egestas risus congue. Et enim malesuada et. Ipsum dolor sit amet, consectetur adipisc.";
		} else if(type.equals("about") == true) {
			text = "about: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Amet, cras sed sapien pretium elementum. Quam nisl vulputate mauris nam ac ullamcorper arcu. Et sed egestas risus congue. Et enim malesuada et. Ipsum dolor sit amet, consectetur adipisc.";
		} else if(type.equals("contact") == true) {
			text = "contact: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Amet, cras sed sapien pretium elementum. Quam nisl vulputate mauris nam ac ullamcorper arcu. Et sed egestas risus congue. Et enim malesuada et. Ipsum dolor sit amet, consectetur adipisc.";
		}
		return text;
	}
	


}
