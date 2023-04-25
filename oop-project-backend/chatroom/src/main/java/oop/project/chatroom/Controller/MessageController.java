package oop.project.chatroom.Controller;

import oop.project.chatroom.Model.Message;
import oop.project.chatroom.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@CrossOrigin(origins = "https://322-frontend-jyl8.vercel.app")
@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping()
    public List<Message> findAll() {
        return messageService.findAll();
    }


    @GetMapping("/{username}")
    public List<Message> findAllForUser(@PathVariable String username) {
        return messageService.findAll(username);
    }


    @PostMapping("/{username}")
    public String createMessage(@RequestBody Message message, @PathVariable String username) {
        return messageService.createMessage(message,username);
    }
}









