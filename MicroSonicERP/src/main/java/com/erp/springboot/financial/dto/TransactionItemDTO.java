package com.erp.springboot.financial.dto;

import java.math.BigDecimal;

import com.erp.springboot.financial.entity.TransactionItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionItemDTO {
	private String pk;
	private int transactionItemIdx;
	private int transactionIdx;
	private String itemCode;
	private String itemName;
	private BigDecimal itemEaPrice; 
	private int amount;
	private int totalPrice;
	
	public TransactionItemDTO(TransactionItems entity) {
		transactionItemIdx = entity.getTransactionItemIdx();
		transactionIdx = entity.getTransactionIdx().getTransactionIdx();
		itemCode = entity.getItemCode().getItemCode();
		itemName = entity.getItemCode().getItemName();
		itemEaPrice = entity.getItemCode().getItemEaPrice();
		amount = entity.getAmount();
		totalPrice = entity.getTotalPrice();
	}
}
