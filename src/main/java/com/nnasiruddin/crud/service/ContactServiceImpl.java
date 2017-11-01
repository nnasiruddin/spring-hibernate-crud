package com.nnasiruddin.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnasiruddin.crud.dao.ContactDAO;
import com.nnasiruddin.crud.entity.Contact;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactDAO contactDAO;

    @Override
    @Transactional
    public List<Contact> searchByCity(String city) {
        return contactDAO.searchByCity(city);
    }

    @Override
    @Transactional
    public List<Contact> searchByState(String state) {
        return contactDAO.searchByState(state);
    }

    @Override
    @Transactional
    public int saveContact(Contact contact) {
        return contactDAO.saveContact(contact);
    }

    @Override
    @Transactional
    public void deleteContact(int id) {
        contactDAO.deleteContact(id);
    }

    @Override
    @Transactional
    public Contact retrieveContact(int id) {
        return contactDAO.retrieveContact(id);
    }

    @Override
    @Transactional
    public void updateContact(Contact contact) {
        contactDAO.updateContact(contact);
    }

    @Override
    @Transactional
    public Contact searchByPhone(String phone) {
        return contactDAO.searchByPhone(phone);

    }

    @Override
    @Transactional
    public Contact searchByEmail(String email) {
        return contactDAO.searchByEmail(email);
    }

}
