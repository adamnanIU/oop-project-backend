package oop.project.chatroom.Patterns;

import oop.project.chatroom.Model.Message;
import oop.project.chatroom.Model.User;
import oop.project.chatroom.Repository.MessageRepository;
import oop.project.chatroom.Repository.UserRepository;

import java.util.List;

public class EditorState implements UserState {
    @Override
    public String addMessage(User user, Message message, UserRepository userRepository, MessageRepository messageRepository) {
        return "You cannot add messages right now you are editing";
    }

    @Override
    public String deleteMessage(Message message, UserRepository userRepository, MessageRepository messageRepository) {
        //Message message1 = messageRepository.getReferenceById(id);
        messageRepository.delete(message);
        return "Message Deleted";
    }

    @Override
    public String deleteUser(String username, UserRepository userRepository) {
        User user1 = null;
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                user1 = user;
                break;
            }

        }
        userRepository.delete(user1);
        return "User deleted";
    }

    @Override
    public void changeState(StateContext stateContext) {
        stateContext.setState(new MessengerState());
    }


}
