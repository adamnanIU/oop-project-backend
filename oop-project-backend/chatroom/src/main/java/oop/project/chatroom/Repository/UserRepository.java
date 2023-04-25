package oop.project.chatroom.Repository;

import oop.project.chatroom.Model.User;
import oop.project.chatroom.Patterns.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
