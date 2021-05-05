package dev.lewishorsley.vaudiencetest.unit;

import dev.lewishorsley.vaudiencetest.model.Address;
import dev.lewishorsley.vaudiencetest.model.Contact;
import dev.lewishorsley.vaudiencetest.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class ContactRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ContactRepository contactRepository;

    @Test
    void testWhenFindAll(){
        Contact firstContact = new Contact();
        firstContact.setName("Bill Smith");
        entityManager.persist(firstContact);
        entityManager.flush();

        Contact secondContact = new Contact();
        secondContact.setName("Bobby Turnball");
        entityManager.persist(secondContact);
        entityManager.flush();

        List<Contact> contacts = contactRepository.findAll();

        assertThat(contacts.size()).isEqualTo(2);
        assertThat(contacts.get(0)).isEqualTo(firstContact);
        assertThat(contacts.get(1)).isEqualTo(secondContact);
    }

    @Test
    void shouldFindByPostcodeAndReturnOneContact() {
        Contact firstContact = new Contact();
        Address firstAddress = new Address();
        firstAddress.setPostcode("EN55OB");
        firstContact.setAddress(firstAddress);

        entityManager.persist(firstContact);
        entityManager.flush();

        Contact secondContact = new Contact();
        Address secondAddress = new Address();
        secondAddress.setPostcode("SW11AB");
        secondContact.setAddress(secondAddress);

        entityManager.persist(secondContact);
        entityManager.flush();

        List<Contact> contacts = contactRepository.findByPostcode(firstContact.getAddress().getPostcode());

        assertThat(contacts.size()).isEqualTo(1);
        assertThat(contacts.get(0)).isEqualTo(firstContact);
    }

    @Test
    void shouldFindByPostcodeAndReturnMultipleContacts() {
        Contact firstContact = new Contact();
        Address firstAddress = new Address();
        firstAddress.setPostcode("EN55OB");
        firstContact.setAddress(firstAddress);

        entityManager.persist(firstContact);
        entityManager.flush();

        Contact secondContact = new Contact();
        Address secondAddress = new Address();
        secondAddress.setPostcode("SW11AB");
        secondContact.setAddress(secondAddress);

        entityManager.persist(secondContact);
        entityManager.flush();

        Contact thirdContact = new Contact();
        Address thirdAddress = new Address();
        thirdAddress.setPostcode("SW11AB");
        thirdContact.setAddress(thirdAddress);

        entityManager.persist(thirdContact);
        entityManager.flush();

        List<Contact> contacts = contactRepository.findByPostcode(secondContact.getAddress().getPostcode());

        assertThat(contacts.size()).isEqualTo(2);
        assertThat(contacts.get(0)).isEqualTo(secondContact);
        assertThat(contacts.get(1)).isEqualTo(thirdContact);
    }
}