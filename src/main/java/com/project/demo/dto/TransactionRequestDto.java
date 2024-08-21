package com.project.demo.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String transactionId;
	private Integer deviceNumber;
	private String geoPosition;
	private Float amount;

}
