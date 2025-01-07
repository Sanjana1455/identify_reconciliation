package com.emotorad.identify_reconciliation.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.emotorad.identify_reconciliation.Model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

	List<Contact> findByEmailOrPhoneNumber(String email, String phoneNumber);

	List<Contact> findByLinkedId(Long id);

}
