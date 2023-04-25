package oop.project.chatroom.Service;

import oop.project.chatroom.Model.Login;
import oop.project.chatroom.Model.User;
import oop.project.chatroom.Patterns.Singleton;
import oop.project.chatroom.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {



    @Autowired
    private UserRepository userRepository;


    public List<User> findAll(){
        return userRepository.findAll();
    }


    public Long createUser(User user){
        List<User> userList = findAll();
        String name = user.getUsername();
        String number = user.getPhoneNumber();
        for (User useri : userList) {
            if (name.equals(useri.getUsername()) || number.equals(useri.getPhoneNumber()))
                throw new IllegalStateException("Phone number or Username already taken");
        }

        User user1 =  userRepository.save(user);

        return user1.getId();
    }








    public Long createAdmin(User user){

        User user1 =  userRepository.save(user);

        Singleton singleton = Singleton.getInstance(user1.getId());

        System.out.println(singleton.toString());
        if(!Objects.equals(singleton.getId(), user.getId())){
            userRepository.delete(user1);
            throw new IllegalStateException("An Admin Already Exists");
        }

        return user1.getId();

    }




    public boolean login(Login login){
        User output = null;
        List<User> userList = findAll();
        String name = login.getUsername();
        String password = login.getPassword();
        for (User useri : userList) {
            if (name.equals(useri.getUsername()) && password.equals(useri.getPassword())) {
                output = useri;
                break;
            }
        }

        if(output != null){
            return output.isAdmin();
        }
        else
            throw new IllegalStateException("Wrong Username or Password");
    }






}
