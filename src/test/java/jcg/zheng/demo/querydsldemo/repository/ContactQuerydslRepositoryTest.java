package jcg.zheng.demo.querydsldemo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import com.querydsl.core.types.Predicate;
import jcg.zheng.demo.querydsldemo.entity.QContact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import jcg.zheng.demo.querydsldemo.entity.Contact;
import jcg.zheng.demo.querydsldemo.entity.ContactType;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ContactQuerydslRepositoryTest {

    @Autowired
    private ContactQuerydslRepository contactQuerydslRepository;


    @Test
    public void it_should_find_contact_after_saved() {

        Contact mary = save("Mary", "Zheng", ContactType.PRIMARY);

        long contactId = 1L;
        Contact found = contactQuerydslRepository.findOne(contactId);
        assertNotNull(found);
        assertEquals("Mary", found.getFirstName());
        assertEquals("Zheng", found.getLastName());
        assertEquals(ContactType.PRIMARY, found.getType());

        Contact alex = save("Alex", "Zheng", ContactType.SEONDARY);
        QContact contEquation = QContact.contact;
        Predicate predicate = contEquation.lastName.eq("Zheng");

        List<Contact> rets = (List<Contact>) contactQuerydslRepository.findAll(predicate);

        assertNotNull(rets);
        assertEquals(2, rets.size());

        contactQuerydslRepository.delete(mary);
        contactQuerydslRepository.delete(alex);
    }


    private Contact save(String firstName, String lastName, ContactType type) {
        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setType(type);
        return contactQuerydslRepository.save(contact);
    }

}
