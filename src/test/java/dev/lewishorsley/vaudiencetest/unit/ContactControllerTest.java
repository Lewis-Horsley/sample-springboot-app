package dev.lewishorsley.vaudiencetest.unit;

import dev.lewishorsley.vaudiencetest.controller.ContactController;
import dev.lewishorsley.vaudiencetest.model.Address;
import dev.lewishorsley.vaudiencetest.model.Contact;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

import dev.lewishorsley.vaudiencetest.service.ContactServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ContactController.class)
class ContactControllerTest{

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ContactServiceImpl contactServiceImpl;

    @Test
    void testShouldAddContact() throws Exception {
        Contact contact = new Contact();
        contact.setName("Bob Smith");

        given(contactServiceImpl.addContact(contact)).willReturn(contact);

        mvc.perform(get("/contacts")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testShouldGetAllContacts() throws Exception {
        Contact contact = new Contact();
        contact.setName("Rick Sanchez");

        List<Contact> allContacts = Collections.singletonList(contact);

        given(contactServiceImpl.getAllContacts()).willReturn(allContacts);

        mvc.perform(get("/contacts")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(contact.getName())));
    }

    @Test
    void testShouldGetContactsByPostcode() throws Exception{
        Contact contact = new Contact();
        Address address = new Address();
        address.setPostcode("SW11SW");
        contact.setAddress(address);

        List<Contact> foundContacts = Collections.singletonList(contact);

        given(contactServiceImpl.getContactByPostcode(contact.getAddress().getPostcode())).willReturn(foundContacts);

        mvc.perform(get("/contacts/" + contact.getAddress().getPostcode())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].address.postcode", is(contact.getAddress().getPostcode())));
    }
}