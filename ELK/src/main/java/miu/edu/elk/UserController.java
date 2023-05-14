package miu.edu.elk;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @GetMapping("/getuser/{id}")
    public User getUser(@PathVariable int id) {

      List<User> users = getUsers();
      User user = users.stream().filter(u -> u.getId() == id).findAny().orElse(null);
        if(user!=null){
            logger.info("User found:{}" , user);
            return user;

        }
        else{
            try{
                throw new Exception();
            }
            catch(Exception e){
                e.printStackTrace();
                logger.error("User not found:{}" , id);

            }
        }
        return new User();
    }
    private List<User> getUsers(){
        return Stream.of(new User(1, "John"), new User(2, "Smith"),new User(3, "Diana"), new User(4, "Tsigab")).collect(Collectors.toList());
    }
}

