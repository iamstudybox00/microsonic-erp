package com.erp.springboot.financial.dto;

import com.erp.springboot.financial.entity.Clients;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModalClientDTO {
	private String clientCode;
	private String companyName;
	private String clientPicName;
	private String clientContact;
	
	public ModalClientDTO(Clients entity) {
		clientCode = entity.getClientCode();
		companyName = entity.getCompanyName();
		clientPicName = entity.getClientPicName();
		clientContact = entity.getClientContact();
	}
}
