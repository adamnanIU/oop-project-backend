package oop.project.chatroom.Patterns;

import oop.project.chatroom.Model.User;
import oop.project.chatroom.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectionOfPhoneNumbers implements Container {
    public List<User> phoneNumbers = new ArrayList<>();

    public CollectionOfPhoneNumbers(UserRepository userRepository) {
        phoneNumbers = userRepository.findAll();
    }



    public List<User> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<User> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public Iterator getIterator() {
        return new phoneNumbersIterator();
    }

    private class phoneNumbersIterator implements Iterator {
        int index;
        @Override
        public boolean hasNext() {
            return index < phoneNumbers.size();
        }

        @Override
        public Object next() {
            if(this.hasNext()){
                return phoneNumbers.get(index++);
            }
            return null;
        }
    }
}
