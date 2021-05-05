package dev.lewishorsley.vaudiencetest.controller;

import dev.lewishorsley.vaudiencetest.model.Contact;
import dev.lewishorsley.vaudiencetest.service.ContactServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ContactController {

    Logger logger = LoggerFactory.getLogger(ContactController.class);

    private final ContactServiceImpl contactServiceImpl;

    public ContactController(ContactServiceImpl contactServiceImpl) {
        this.contactServiceImpl = contactServiceImpl;
    }

    @PostMapping("/contacts")
    @ResponseStatus(HttpStatus.OK)
    public Contact addContact(@RequestBody Contact contact){

        Contact newContact = contactServiceImpl.addContact(contact);
        logger.info("Save successful.");
        return newContact;
    }

    @GetMapping("/contacts")
    @ResponseStatus(HttpStatus.OK)
    public List<Contact> getAllContacts(){
        return contactServiceImpl.getAllContacts();
    }

    @GetMapping(path = "/contacts/{postcode}")
    public List<Contact> getContactsByPostcode(@PathVariable("postcode") String postcode){
        return contactServiceImpl.getContactByPostcode(postcode);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
