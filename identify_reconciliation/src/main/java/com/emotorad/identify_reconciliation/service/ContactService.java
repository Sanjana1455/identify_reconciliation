package com.emotorad.identify_reconciliation.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emotorad.identify_reconciliation.DTO.ContactResponse;
import com.emotorad.identify_reconciliation.Model.Contact;
import com.emotorad.identify_reconciliation.repository.ContactRepository;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public ContactResponse handleContactRequest(String email, String phoneNumber) {
        List<Contact> existingContacts = contactRepository.findByEmailOrPhoneNumber(email, phoneNumber);

        if (existingContacts.isEmpty()) {
            Contact primaryContact = createPrimaryContact(email, phoneNumber);
            return createResponse(primaryContact, null);
        }

        Contact primaryContact = findOrDeterminePrimaryContact(existingContacts);
        
        boolean isNewCombination = true;
        for (Contact contact : existingContacts) {
            if (email.equals(contact.getEmail()) && phoneNumber.equals(contact.getPhoneNumber())) {
                isNewCombination = false;
                break;
            }
        }

        if (isNewCombination) {
            Contact secondaryContact = createSecondaryContact(email, phoneNumber, primaryContact.getId());
            return createResponse(primaryContact, secondaryContact);
        }

        return createResponse(primaryContact, null);
    }

    private Contact createPrimaryContact(String email, String phoneNumber) {
        Contact primaryContact = new Contact();
        primaryContact.setEmail(email);
        primaryContact.setPhoneNumber(phoneNumber);
        primaryContact.setLinkPrecedence("primary");
        return contactRepository.save(primaryContact);
    }

    private Contact createSecondaryContact(String email, String phoneNumber, Long primaryId) {
        Contact secondaryContact = new Contact();
        secondaryContact.setEmail(email);
        secondaryContact.setPhoneNumber(phoneNumber);
        secondaryContact.setLinkPrecedence("secondary");
        secondaryContact.setLinkedId(primaryId);
        return contactRepository.save(secondaryContact);
    }

    private Contact findOrDeterminePrimaryContact(List<Contact> contacts) {
        Contact primaryContact = null;
        
        for (Contact contact : contacts) {
            if ("primary".equals(contact.getLinkPrecedence())) {
                primaryContact = contact;
                break;
            }
        }

        if (primaryContact == null) {
            Contact firstContact = contacts.get(0);
            if (firstContact.getLinkedId() != null) {
                primaryContact = contactRepository.findById(firstContact.getLinkedId()).orElse(null);
            }
            
            if (primaryContact == null) {
                primaryContact = contacts.get(0);
                for (Contact contact : contacts) {
                    if (contact.getCreatedAt().isBefore(primaryContact.getCreatedAt())) {
                        primaryContact = contact;
                    }
                }
            }
        }
        return primaryContact;
    }

    private ContactResponse createResponse(Contact primaryContact, Contact secondaryContact) {
        ContactResponse response = new ContactResponse();
        response.setPrimaryContactId(primaryContact.getId());
        
        Set<String> emails = new LinkedHashSet<>();
        Set<String> phoneNumbers = new LinkedHashSet<>();
        Set<Long> secondaryIds = new LinkedHashSet<>();

        emails.add(primaryContact.getEmail());
        phoneNumbers.add(primaryContact.getPhoneNumber());

        if (secondaryContact != null) {
            emails.add(secondaryContact.getEmail());
            phoneNumbers.add(secondaryContact.getPhoneNumber());
            secondaryIds.add(secondaryContact.getId());
        }

        List<Contact> secondaryContacts = contactRepository.findByLinkedId(primaryContact.getId());
        for (Contact secContact : secondaryContacts) {
            emails.add(secContact.getEmail());
            phoneNumbers.add(secContact.getPhoneNumber());
            secondaryIds.add(secContact.getId());
        }

        response.setEmails(new ArrayList<>(emails));
        response.setPhoneNumbers(new ArrayList<>(phoneNumbers));
        response.setSecondaryContactIds(new ArrayList<>(secondaryIds));
        response.setLinkPrecedence(secondaryContact != null ? "secondary" : "primary");

        return response;
    }
}
