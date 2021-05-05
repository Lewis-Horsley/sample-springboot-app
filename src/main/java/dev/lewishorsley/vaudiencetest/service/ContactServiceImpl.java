package dev.lewishorsley.vaudiencetest.service;

import dev.lewishorsley.vaudiencetest.model.Contact;
import dev.lewishorsley.vaudiencetest.repository.ContactRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact addContact(@NotNull Contact contact){
        logger.info("Saving Contact: {}", contact.toString());
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> getAllContacts(){
        return this.contactRepository.findAll();
    }

    @Override
    public List<Contact> getContactByPostcode(String postcode){
        return contactRepository.findByPostcode(postcode);
    }
}
