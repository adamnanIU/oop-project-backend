package oop.project.chatroom.Patterns;

import oop.project.chatroom.Model.Message;
import oop.project.chatroom.Model.User;
import oop.project.chatroom.Repository.MessageRepository;
import oop.project.chatroom.Repository.UserRepository;

public class StateContext implements UserState {

    private UserState state;


    public StateContext() {
        this.state = new MessengerState();
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    @Override
    public String addMessage(User user, Message message, UserRepository userRepository, MessageRepository messageRepository) {
        return this.state.addMessage(user,message,userRepository,messageRepository);
    }

    @Override
    public String deleteMessage(Message message, UserRepository userRepository, MessageRepository messageRepository) {

        return this.state.deleteMessage(message,userRepository,messageRepository);
    }

    @Override
    public String deleteUser(String username, UserRepository userRepository) {

        return this.state.deleteUser(username,userRepository);
    }

    @Override
    public void changeState(StateContext stateContext) {

        this.state.changeState(this);

    }


}
