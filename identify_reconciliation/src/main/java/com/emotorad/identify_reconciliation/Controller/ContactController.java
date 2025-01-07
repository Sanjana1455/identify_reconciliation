package com.emotorad.identify_reconciliation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emotorad.identify_reconciliation.DTO.ContactRequest;
import com.emotorad.identify_reconciliation.DTO.ContactResponse;
import com.emotorad.identify_reconciliation.service.ContactService;

@RestController
@RequestMapping("/identify")
public class ContactController {
	
	@Autowired
    private ContactService contactService;

    @PostMapping
    public ContactResponse identifyContact(@RequestBody ContactRequest contactRequest) {
        return contactService.handleContactRequest(contactRequest.getEmail(), contactRequest.getPhoneNumber());
    }

}
