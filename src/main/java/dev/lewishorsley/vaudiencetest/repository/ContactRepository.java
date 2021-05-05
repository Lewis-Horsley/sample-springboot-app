package dev.lewishorsley.vaudiencetest.repository;

import dev.lewishorsley.vaudiencetest.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {

    @Query("FROM Contact WHERE postcode = ?1")
    List<Contact> findByPostcode(String postcode);
}
