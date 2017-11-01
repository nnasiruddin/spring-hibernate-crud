package com.nnasiruddin.crud.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import com.nnasiruddin.crud.controller.ContactController;
import com.nnasiruddin.crud.entity.Contact;
import com.nnasiruddin.crud.service.ContactService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:WEB-INF/nnasiruddin-coding-servlet.xml")
public class ContactControllerTest {

    @Autowired WebApplicationContext wac;
    @Autowired MockHttpSession session;
    @Autowired MockHttpServletRequest request;

    @Mock
    ContactService contactService;

    @InjectMocks
    ContactController contactController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
    }

    // =========================================== Get All Users ==========================================

    @Test
    public void testListByCity() throws Exception {
        List<Contact> contacts = new ArrayList<Contact>();
        Contact contact = new Contact();
        contact.setName("Nuruddin");
        contact.setId(1);
        contacts.add(contact);

        Contact contact2 = new Contact();
        contact2.setId(2);
        contact2.setName("Jhon");
        contacts.add(contact2);



        when(contactService.searchByCity(Matchers.anyString())).thenReturn(contacts);

        MvcResult result = mockMvc.perform(get("/contact/listByCity/Chicago"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();
        String content = result.getResponse().getContentAsString();

        Assert.assertNotNull(content);
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(content);

        Assert.assertEquals(2, array.size());

        JSONObject obj = (JSONObject)array.get(0);
        String name = (String)obj.get("name");
        Assert.assertEquals("Nuruddin", name);

        JSONObject obj2 = (JSONObject)array.get(1);
        String name2 = (String)obj2.get("name");
        Assert.assertEquals("Jhon", name2);
    }

    @Test
    public void testListByCityWhenCityEmpty() throws Exception {

        MvcResult result = mockMvc.perform(get("/contact/listByCity/ "))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();
    }
}