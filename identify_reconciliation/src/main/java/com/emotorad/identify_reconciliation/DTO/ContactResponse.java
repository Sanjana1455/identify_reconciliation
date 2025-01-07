package com.emotorad.identify_reconciliation.DTO;

import java.util.List;

import lombok.Data;


@Data
public class ContactResponse {
	private Long primaryContactId;
    private List<String> emails;
    private List<String> phoneNumbers;
    private List<Long> secondaryContactIds;
    private String linkPrecedence;


	
}
