package com.example.retobicicleta.Controller;
import com.example.retobicicleta.Model.Client;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.retobicicleta.Model.Message;
import com.example.retobicicleta.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")

@CrossOrigin(origins="*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/Message")
public class MessageController {
       @Autowired
       private MessageService messageService;


    @GetMapping("/all")
        public List<Message> getAll(){
            return messageService.getAll();
        }

        @GetMapping("/{id}")
        public Optional<Message> getMessage(@PathVariable("id") int id) {
            return messageService.getMessage(id);
        }

        @PostMapping("/save")
        @ResponseStatus(HttpStatus.CREATED)
        public Message save(@RequestBody Message message) {
            return messageService.save(message);
        }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Message update(@RequestBody Message message){return messageService.update(message);}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){return messageService.deleteMessage(id);}
    }




