package com.nnasiruddin.crud.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nnasiruddin.crud.entity.Contact;
import com.nnasiruddin.crud.exception.ResourceException;
import com.nnasiruddin.crud.service.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactController {
    // need to inject our customer service
    @Autowired
    private ContactService contactService;

    /**
     * This end point displays all records with the city specified
     * @param city - the city to search for
     * @return JSON - A JSON Array containing records with the same city
     */

    @RequestMapping(value = "/listByCity/{city}", method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> listContactsByCity(@PathVariable("city") String city) {
        if(city == null || StringUtils.isEmpty(city.trim())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "City cannot be null or emtpy"));
        }
        // get contacts from the service
        List<Contact> contactsByCity = contactService.searchByCity(city);

        return ResponseEntity.status(HttpStatus.OK).body(contactsByCity);
    }

    /**
     * This end point will retrieve all records with the same state
     * @param state - the state to search for
     * @return JSONArray - All records from the state specified
     */

    @RequestMapping(value = "/listByState/{state}", method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<Object> listContactsByState(@PathVariable("state") String state) {
        if(state == null || StringUtils.isEmpty(state)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "State cannot be null or emtpy"));
        }
        // get contacts from the service
        List<Contact> contactsByState = contactService.searchByState(state);

        return ResponseEntity.status(HttpStatus.OK).body(contactsByState);
    }

    /**
     * This endpoint will retrieve the record with the email specified
     * @param email - the email to search with
     * @return JSONObject - Contact with the email specified
     */
    @RequestMapping(value = "/searchByEmail", method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> searchByEmail(@RequestParam(value = "email", required = true) String email) {

        try {
            // get contacts from the service
            Contact contact = contactService.searchByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(contact);
        } catch (Exception e) {
            throw new ResourceException(HttpStatus.NOT_FOUND, "We were unable to find the specified resource.");
        }
    }

    /**
     * This end point will retrieve a record with the phone number specified it will search personal and work phone
     * @param phone - either work or personal phone number
     * @return JSONObject - Contact with the phone specified
     */
    @RequestMapping(value = "/searchByPhone/{phone}", method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> searchByPhone(@PathVariable("phone") String phone) {

        try {
            // get contacts from the service
            Contact contact = contactService.searchByPhone(phone);
            return ResponseEntity.status(HttpStatus.OK).body(contact);
        } catch (Exception e) {
            throw new ResourceException(HttpStatus.NOT_FOUND, "We were unable to find the specified resource.");
        }
    }

    /**
     * Creates a contact and stores it. All fields required
     * @param contact - contact to store
     * @return id - id of the contact created
     */
    @RequestMapping(value = "/saveContact", method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> saveContact(@RequestBody Contact contact) {

        try{
            int id = contactService.saveContact(contact);
            // get contacts from the service
            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Id", id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "All fields are required"));
        }
    }

    /**
     * This end point will update the contact specified
     * @param contact - contact to update
     */
    @RequestMapping(value = "/updateContact", method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(value = HttpStatus.OK)
    public void updateContact(@RequestBody Contact contact) {

        // update contact from the service
        contactService.updateContact(contact);
    }

    /**
     * This end point will delete the contact with the id specified
     * @param theId - id to delete
     * @return JSONObject - status
     */
    @RequestMapping(value = "/deleteContact/{id}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> deleteContact(@PathVariable("id") int theId) {


        try {
            // delete the contact
            contactService.deleteContact(theId);
            return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("status", "OK"));
        } catch (Exception e) {
            throw new ResourceException(HttpStatus.NOT_FOUND, "We were unable to find the specified resource.");
        }
    }
}