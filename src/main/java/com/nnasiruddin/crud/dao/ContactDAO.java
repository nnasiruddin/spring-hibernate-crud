package com.nnasiruddin.crud.dao;

import java.util.List;

import com.nnasiruddin.crud.entity.Contact;

public interface ContactDAO {
    public List<Contact> searchByCity(String city);
    public List<Contact> searchByState(String state);
    public int saveContact(Contact contact);
    public void deleteContact(int id);
    public Contact retrieveContact(int id);
    public void updateContact(Contact contact);
    public Contact searchByPhone(String phone);
    public Contact searchByEmail(String email);
}
