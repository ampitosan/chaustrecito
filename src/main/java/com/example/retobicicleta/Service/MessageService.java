package com.example.retobicicleta.Service;


import com.example.retobicicleta.Model.Category;
import com.example.retobicicleta.Model.Message;
import com.example.retobicicleta.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll() {
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int id) {
        return messageRepository.getMessage(id);
    }

    public Message save(Message message) {
        if (message.getIdMessage() == null) {
            return messageRepository.save(message);
        } else {
            Optional<Message> messageencontrado = getMessage(message.getIdMessage());
            if (messageencontrado.isEmpty()) {
                return messageRepository.save(message);
            } else {
                return message;
            }

        }
    }

    public Message update(Message message) {
        if (message.getIdMessage() != null) {
            Optional<Message> messageencontrado = getMessage(message.getIdMessage());
            if (!messageencontrado.isEmpty()) {
                if (message.getMessageText() != null) {
                    messageencontrado.get().setMessageText(message.getMessageText());

                }
                return messageRepository.save(messageencontrado.get());

            }
        }
        return message;

    }
    public boolean deleteMessage(int id){
        boolean resultado = getMessage(id).map(messageporeliminar ->{
            messageRepository.delete(messageporeliminar);
            return true;
        }).orElse(false);
        return resultado;
    }
    public Optional<Message> getMessageId(int id){
        return messageRepository.getMessage(id);}
}
