package com.accenture.ra.criteria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.ra.criteria.dao.SearchUserFilter;
import com.accenture.ra.criteria.dao.UserDao;
import com.accenture.ra.criteria.entity.User;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
	@Autowired
	private UserDao userDao;
	
	@GetMapping(path = "/user/{uname}")
	public ResponseEntity<User> getUseById(@PathVariable String uname) {
		return ResponseEntity.ok(
			this.userDao.findByUsername(uname)
		);
	}
	
	@GetMapping(path = "/user/search-by")
	public ResponseEntity<List<User>> seachUserBy(SearchUserFilter searchUserFilter) {
		log.info("{}", searchUserFilter);
		return ResponseEntity.ok(
			this.userDao.searchUserBy(searchUserFilter)
		);
	}
}