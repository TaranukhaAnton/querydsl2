package jcg.zheng.demo.querydsldemo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;


import jcg.zheng.demo.querydsldemo.entity.Contact;


public interface ContactQuerydslRepository extends JpaRepository<Contact, Long>, QueryDslPredicateExecutor<Contact> {
	

	
}
