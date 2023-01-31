package com.accenture.ra.criteria.dao;

//@Data
//public class SearchUserParam {
//	private String firstname;
//	private String lastname;
//	private String username;
//	private String email;
//}

public record SearchUserFilter(
  	 String id,
	 String firstname,
	 String lastname,
	 String username,
   	 String email,
   	 String age,
   	 String rangeMinAge,
   	 String rangeMaxAge
   ) { }