package com.bean.entity;

import lombok.Data;

public class MemberDTO {

	
	@Data
	public class User {
		
		private String cust_id;
		private String cust_pw;
		private String cust_name;
		private String cust_address;
		private int cust_phone;
		
	}
}
