package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Contact {
    
    public Contact() {
        
    }
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String addressLine1;
    private String addressLine2;
    private String country;
    private String city;
    private String phoneNumber;
    
    public Long getId() {
        return id;
    }
    public String getAddressLine1() {
        return addressLine1;
    }
    public String getAddressLine2() {
        return addressLine2;
    }
    public String getCountry() {
        return country;
    }
    public String getCity() {
        return city;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static class Builder{
        Contact contact = null;
        
        public Builder() {
            contact = new Contact();
        }
        
        public Builder withAddressLine1(String add1) {
            contact.addressLine1 = add1;
            return this;
        }
        public Builder withAddressLine2(String add2) {
            contact.addressLine2 = add2;
            return this;
        }
        
        public Builder withPhoneNumber(String phNumber) {
            contact.phoneNumber = phNumber;
            return this;
        }
        
        public Builder withCity(String city) {
            contact.city = city;
            return this;
        }
        
        public Builder withCountry(String country) {
            contact.country = country;
            return this;
        }
        
        
        
        public Contact build() {
            return contact;
        }
        
        
    }
    

}
