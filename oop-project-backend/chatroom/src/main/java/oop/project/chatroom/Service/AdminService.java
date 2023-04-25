package oop.project.chatroom.Service;

import com.twilio.Twilio;
import oop.project.chatroom.Model.Message;
import oop.project.chatroom.Model.User;
import oop.project.chatroom.Patterns.MessengerState;
import oop.project.chatroom.Patterns.StateContext;
import oop.project.chatroom.Repository.MessageRepository;
import oop.project.chatroom.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    public  String ACCOUNT_SID = "ACb0d79e1f5f6864bab978474d6580b08b";
    public  String AUTH_TOKEN = "14bc8a6042183ee28aca85615744a658";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

   private StateContext stateContext = new StateContext();

    public User findAdmin(){
        List<User> userList = userRepository.findAll();
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).isAdmin())
                return userList.get(i);

        }
        return null;
    }



    public String changeState(){
        System.out.println(stateContext.getState());
        stateContext.changeState(stateContext);
        System.out.println(stateContext.getState());
        return "State Changed";
    }

    public String deleteMessage(Message message){
        return stateContext.deleteMessage(message,userRepository,messageRepository);
    }


    public String deleteUser(String username){
        return stateContext.deleteUser(username,userRepository);
    }

    public String addMessage(Message message){
        return stateContext.addMessage(findAdmin(),message,userRepository,messageRepository);
    }



    public String sendSMS(User user, User sender){

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(
                        new com.twilio.type.PhoneNumber(user.getPhoneNumber()),
                        new com.twilio.type.PhoneNumber("+18444361601"),
                        sender.getUsername() + " just posted a new message. Check it out!")
                .create();
        return "Message Sent";
    }









}
