package oop.project.chatroom.Service;

import com.twilio.Twilio;
import oop.project.chatroom.Model.Message;
import oop.project.chatroom.Model.User;
import oop.project.chatroom.Patterns.CollectionOfPhoneNumbers;
import oop.project.chatroom.Patterns.Iterator;
import oop.project.chatroom.Repository.MessageRepository;
import oop.project.chatroom.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Service
public class MessageService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;



    public  String ACCOUNT_SID = "ACb0d79e1f5f6864bab978474d6580b08b";
    public  String AUTH_TOKEN = "32439f96f99474f37166838a1772fcc8";



    public List<Message> findAll(String username) {
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            if (user.getUsername().equals(username))
                return user.getMessages();
        }
        return null;
    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }


    public String createMessage(Message message, String username) {
        User user1 = null;
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            if (user.getUsername().equals(username)){
                user1 = user;
                break;
            }
        }
        message.setMessageOwner(user1);
        Message message1 = messageRepository.save(message);
        List<Message> messageList = user1.getMessages();
        messageList.add(message1);
        user1.setMessages(messageList);


        CollectionOfPhoneNumbers collectionOfPhoneNumbers = new CollectionOfPhoneNumbers(userRepository);

        for(Iterator iter = collectionOfPhoneNumbers.getIterator(); iter.hasNext();){
            User iterationUser = (User) iter.next();
            if(iterationUser != user1){
                System.out.println("Went into loop");
               System.out.println(sendSMS(iterationUser, user1));
            }
        }









        return user1.getUsername();

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
