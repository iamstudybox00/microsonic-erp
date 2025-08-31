package com.erp.springboot.financial.dto;

import java.math.BigDecimal;

import com.erp.springboot.financial.entity.Items;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModalItemDTO {
	private String itemCode;
	private String itemName;
	private String itemPic;
	private BigDecimal itemEaPrice;
	
	public ModalItemDTO(Items entity) {
		itemCode = entity.getItemCode();
		itemName = entity.getItemName();
		itemPic = entity.getItemPic();
		itemEaPrice = entity.getItemEaPrice();
	}
}
