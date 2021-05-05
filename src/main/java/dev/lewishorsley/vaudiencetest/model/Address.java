package dev.lewishorsley.vaudiencetest.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

    @Column(name = "ADDRESS_FIELD")
    private String addressField;
    @Column(name = "ADDRESS_FIELD_2")
    private String addressField2;
    @Column(name = "CITY")
    private String city;
    @Column(name = "POSTCODE")
    private String postcode;


    public String getAddressField() {
        return addressField;
    }

    public void setAddressField(String addressField) {
        this.addressField = addressField;
    }

    public String getAddressField2() {
        return addressField2;
    }

    public void setAddressField2(String addressField2) {
        this.addressField2 = addressField2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "{" +
                "addressField='" + addressField + '\'' +
                ", addressField2='" + addressField2 + '\'' +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }
}