package jcg.zheng.demo.querydsldemo.service;

import jcg.zheng.demo.querydsldemo.entity.Contact;
import jcg.zheng.demo.querydsldemo.entity.ContactType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ContactServiceTest  {

    @Autowired
    private ContactService contactService;



    @Test
    public void it_should_find_zero_contact_when_searchByName_with_non_exist_data() {
        List found = contactService.searchByName("Bob", null);
        assertTrue(found.isEmpty());
    }

    @Test
    public void it_should_find_contact_when_searchByName_with_exist_data() {
        Contact contact = contactService.save( "John", "Zheng", ContactType.PRIMARY);
        assertNotNull(contact);
        List<Contact> found = contactService.searchByName("John", null);
        assertEquals(1, found.size());
        assertEquals("John", found.get(0).getFirstName());
        assertEquals("Zheng", found.get(0).getLastName());
        assertEquals(ContactType.PRIMARY, found.get(0).getType());
        contactService.delete(contact);
    }

}