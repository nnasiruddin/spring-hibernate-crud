package com.nnasiruddin.crud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.nnasiruddin.crud.entity.Contact;

@Repository
public class ContactDAOImpl implements ContactDAO {
    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Contact> searchByCity(String theCity) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // get contact where who belong to a city
        Query theQuery = currentSession.createQuery("from Contact where city=:theCity", Contact.class);
        theQuery.setParameter("theCity", theCity);

        // execute query and get result list
        List<Contact> contacts = theQuery.getResultList();

        return contacts;
    }

    @Override
    public List<Contact> searchByState(String theState) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // get contact where who belong to a city
        Query theQuery = currentSession.createQuery("from Contact where state=:theState", Contact.class);
        theQuery.setParameter("theState", theState);

        // execute query and get result list
        List<Contact> contacts = theQuery.getResultList();

        return contacts;
    }

    @Override
    public int saveContact(Contact contact) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // save/upate the contact ...
        Integer id = (Integer) currentSession.save(contact);

        return id;
    }

    @Override
    public void deleteContact(int id) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // delete object with primary key
        Query theQuery = currentSession.createQuery("delete from Contact where id=:id");
        theQuery.setParameter("id", id);

        theQuery.executeUpdate();
    }

    @Override
    public Contact retrieveContact(int id) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // now retrieve/read from database using the primary key
        Contact contact = currentSession.get(Contact.class, id);

        return contact;
    }

    @Override
    public void updateContact(Contact contact) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // save/upate the contact
        currentSession.saveOrUpdate(contact);

    }

    @Override
    public Contact searchByPhone(String phone) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // get object with phone
        Query theQuery = currentSession.createQuery("from Contact where phone_personal=:phone or phone_work=:phone", Contact.class);
        theQuery.setParameter("phone", phone);

        // execute query and get result list
        List<Contact> contacts = theQuery.getResultList();

        // return the results
        return contacts.get(0);
    }

    @Override
    public Contact searchByEmail(String email) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // get object with phone
        Query theQuery = currentSession.createQuery("from Contact where email=:email", Contact.class);
        theQuery.setParameter("email", email);

        // execute query and get result list
        List<Contact> contacts = theQuery.getResultList();

        // return the results
        return contacts.get(0);
    }
}
