package dev.lewishorsley.vaudiencetest.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Embedded
    private Address address;


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        try {
            return "{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", dateOfBirth=" + dateOfBirth +
                    ", address=" + address.toString() +
                    '}';
        }catch (NullPointerException ex){
            return "{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", dateOfBirth=" + dateOfBirth +
                    ", address= none" +
                    '}';
        }
    }
}
