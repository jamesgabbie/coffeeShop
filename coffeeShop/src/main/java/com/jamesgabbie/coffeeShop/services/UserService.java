package com.jamesgabbie.coffeeShop.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamesgabbie.coffeeShop.models.User;
import com.jamesgabbie.coffeeShop.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository uRepo;
	
	// Find user by UserName
    public User findByUsername(String email) {
        return uRepo.findByEmail(email);
    }
    
 // Check For Master User
    public String checkForMasterUser() {
        List<User> allUsers = uRepo.findAll();
        String i = new String();
        if(allUsers.size() == 1) {
        	i = "true";
        }
        if(allUsers.size() == 0) {
        	i = "false";
        }
        if(allUsers.size() >= 2) {
        	i = "false";
        }
        return i;
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
 	
 	//Create User
 	public User registerUser(User user) {
	    String hashedPw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
	    user.setPassword(hashedPw);
	    return uRepo.save(user);
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
	
	
	


}
