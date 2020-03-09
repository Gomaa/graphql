package com.example.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Table
@Entity

public class Party {

	
    public Party() {
    }
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private  String name;
	private  String alias;
	private  String status;
	@OneToMany
	private List<Contact> contacts;
	
	
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAlias() {
        return alias;
    }
    public String getStatus() {
        return status;
    }
    public List<Contact> getContacts(){
        return contacts;
    }
    
   
   public static class Builder{
       private Party aParty;
       
       public Builder() {
           aParty = new Party();
       }
       
       public Builder withName (String aName) {
           aParty.name = aName;
           return this;
       }
       
       public Builder withAlias(String anAlias) {
           aParty.alias = anAlias;
           return this;
       }
       
       public Builder withStatus(String aStatus) {
           aParty.status = aStatus;
           return this;
       }
       
       public Builder withContacts(List<Contact> theContacts) {
           aParty.contacts = theContacts;
           return this;
       }
       
       public Party build() {
           return aParty;
       }
       
   }
	
	
	
}
