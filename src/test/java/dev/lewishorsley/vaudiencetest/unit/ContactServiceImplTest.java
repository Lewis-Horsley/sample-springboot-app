package dev.lewishorsley.vaudiencetest.unit;

import dev.lewishorsley.vaudiencetest.model.Address;
import dev.lewishorsley.vaudiencetest.model.Contact;
import dev.lewishorsley.vaudiencetest.repository.ContactRepository;
import dev.lewishorsley.vaudiencetest.service.ContactServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ContactServiceImplTest {

    @MockBean
    private ContactRepository contactRepository;

    @Autowired
    private ContactServiceImpl contactServiceImpl;

    @Test
    void shouldAddContact() {
        Contact contact = new Contact();
        Address address = new Address();
        contact.setName("Jimi Hendrix");
        contact.setAddress(address);

        given(contactRepository.save(contact)).willReturn(contact);

        Contact result = contactServiceImpl.addContact(contact);

        assertThat(result).isEqualTo(contact);
    }

    @Test
    void shouldGetAllContacts() {
        Contact firstContact = new Contact();
        Address firstAddress = new Address();
        firstContact.setName("Jimi Hendrix");
        firstContact.setAddress(firstAddress);

        Contact secondContact = new Contact();
        Address secondAddress = new Address();
        secondContact.setName("Bobby Turnball");
        secondContact.setAddress(secondAddress);

        List<Contact> contacts = new ArrayList<>();
        contacts.add(firstContact);
        contacts.add(secondContact);

        given(contactRepository.findAll()).willReturn(contacts);

        List<Contact> result = contactServiceImpl.getAllContacts();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0)).isEqualTo(firstContact);
        assertThat(result.get(1)).isEqualTo(secondContact);
    }

    @Test
    void shouldGetContactsByPostcode() {
        Contact firstContact = new Contact();
        firstContact.setName("Jimi Hendrix");
        Address firstAddress = new Address();
        firstAddress.setPostcode("EN554F");
        firstContact.setAddress(firstAddress);

        Contact secondContact = new Contact();
        secondContact.setName("Paul Hendrix");
        Address secondAddress = new Address();
        secondAddress.setPostcode("EN554F");
        secondContact.setAddress(secondAddress);

        List<Contact> contacts = new ArrayList<>();
        contacts.add(firstContact);
        contacts.add(secondContact);

        given(contactRepository.findByPostcode(firstContact.getAddress().getPostcode())).willReturn(contacts);

        List<Contact> result = contactServiceImpl.getContactByPostcode(firstContact.getAddress().getPostcode());
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0)).isEqualTo(firstContact);
        assertThat(result.get(1)).isEqualTo(secondContact);
    }
}