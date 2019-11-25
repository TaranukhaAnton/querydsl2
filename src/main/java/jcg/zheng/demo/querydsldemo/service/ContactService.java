package jcg.zheng.demo.querydsldemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import jcg.zheng.demo.querydsldemo.entity.QContact;
import jcg.zheng.demo.querydsldemo.repository.ContactQuerydslRepository;

import jcg.zheng.demo.querydsldemo.entity.Contact;
import jcg.zheng.demo.querydsldemo.entity.ContactType;

@Service
public class ContactService {

    @Autowired
    private ContactQuerydslRepository contactDslRepo;

    public List<Contact> searchByName(String firstName, String lastName) {
        List<Contact> ret = new ArrayList<>();
        QContact contEquation = QContact.contact;
        Predicate cnt = contEquation.firstName.contains(firstName);
        Iterable<Contact> contacts = contactDslRepo.findAll(cnt);
        for (Contact e : contacts) {
            ret.add(e);
        }
        return ret;
    }

    public Contact save(String firstName, String lastName, ContactType type) {
        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setType(type);

        return contactDslRepo.save(contact);
    }

    public void delete(Contact contact) {
        contactDslRepo.delete(contact);
    }

}