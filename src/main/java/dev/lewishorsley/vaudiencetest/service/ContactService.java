package dev.lewishorsley.vaudiencetest.service;

import dev.lewishorsley.vaudiencetest.model.Contact;

import java.util.List;

public interface ContactService {

    Contact addContact(Contact contact);

    List<Contact> getAllContacts();

    List<Contact> getContactByPostcode(String postcode);
}
