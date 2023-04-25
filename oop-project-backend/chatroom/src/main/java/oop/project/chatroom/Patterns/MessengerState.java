package oop.project.chatroom.Patterns;

import com.twilio.Twilio;
import oop.project.chatroom.Model.Message;
import oop.project.chatroom.Model.User;
import oop.project.chatroom.Repository.MessageRepository;
import oop.project.chatroom.Repository.UserRepository;

import java.util.List;

public class MessengerState implements UserState {
    public  String ACCOUNT_SID = "ACb0d79e1f5f6864bab978474d6580b08b";
    public  String AUTH_TOKEN = "14bc8a6042183ee28aca85615744a658";
    @Override
    public String addMessage(User user2, Message message, UserRepository userRepository, MessageRepository messageRepository) {
        User user1 = null;
        String username = user2.getUsername();
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


        return "Message sent";
    }

    @Override
    public String deleteMessage(Message message, UserRepository userRepository, MessageRepository messageRepository) {
        return "You cannot delete messages right now";
    }

    @Override
    public String deleteUser(String username, UserRepository userRepository) {
        return "You cannot delete users right now";
    }

    @Override
    public void changeState(StateContext stateContext) {
        stateContext.setState(new EditorState());

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
