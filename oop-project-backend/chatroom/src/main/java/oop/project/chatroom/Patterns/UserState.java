package oop.project.chatroom.Patterns;

import oop.project.chatroom.Model.Message;
import oop.project.chatroom.Model.User;
import oop.project.chatroom.Repository.MessageRepository;
import oop.project.chatroom.Repository.UserRepository;

public interface UserState {
    public String addMessage(User user, Message message, UserRepository userRepository, MessageRepository messageRepository);
    public String deleteMessage(Message message, UserRepository userRepository, MessageRepository messageRepository);
    public String deleteUser(String username, UserRepository userRepository);

    public void changeState(StateContext stateContext);



}
