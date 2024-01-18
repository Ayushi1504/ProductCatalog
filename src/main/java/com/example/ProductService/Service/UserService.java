package com.example.ProductService.Service;

import com.example.ProductService.Model.User;
import com.example.ProductService.Repository.UserRepository;
import com.example.ProductService.Util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    LoggerUtil userServiceLogger = new LoggerUtil(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public boolean deleteUser(String id,String password) {

        try {
            if (validateString(id)) {
                User user = getUserByIdAndPassword(id, password);
                if(user!=null) {
                    userRepository.deleteById(id);
                    userServiceLogger.info("User " + id + " has been deleted.");
                    return true;
                }
                else userServiceLogger.warn("Provided password or username is wrong!");
            }
        }
        catch(Exception e) {
            userServiceLogger.error("The provided user is not valid!",e.getCause());
        }

        return false;
    }

    public User updateUser(String id, User user) {

        User responseUser = getUserByIdAndPassword(id,user.getPassword());
        if(responseUser!=null){
            if(!responseUser.getEmail().equalsIgnoreCase(user.getEmail())){
                userServiceLogger.error("Updating Email is not allowed! Please create a new account", new Exception("Email Can not be updated!"));
                return null;
            }
            userRepository.save(user);
            userServiceLogger.info("Details have been updated!");
            return user;
        }
        else userServiceLogger.warn("Details provided are wrong, Please check again!");
        return null;
    }

    public User getUserByIdAndPassword(String id, String password) {
        try {
            if (validateString(id) && validateString(password)) {
                Optional<User> response = userRepository.findById(id);
                if(!response.isEmpty()) {
                    userServiceLogger.info("User "+id+" retrieved.");
                    if(response.get().getPassword().equalsIgnoreCase(password)){
                        return response.get();
                    }
                    else {
                        userServiceLogger.warn("Provided password or username is wrong!");
                        return null;
                    }
                }
                else userServiceLogger.error("User not found!",new Exception("User Not Found!"));
                return null;
            }
        }
        catch(Exception e) {
            userServiceLogger.error("The provided user is not valid!",e.getCause());
        }

        return null;
    }

    public User createUser(User user) {
        try {
            if (validateString(user.getName()) && validateString(user.getEmail()) ){
                Optional<User> response = userRepository.findByEmail(user.getEmail());
                if(response.get()==null){
                    user.setId( UUID. randomUUID().toString());
                    userRepository.save(user);
                    return user;
                }
                else userServiceLogger.warn("User with provided Email already exists!");
                return null;

            }
        }
        catch(Exception e) {
            userServiceLogger.error("The provided user is not valid!",e.getCause());
        }

        return null;
    }


    public boolean validateString(String input){
        if(input!=null && !input.isBlank() && !input.isEmpty()) return true;
        return false;
    }
}
