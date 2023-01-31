package com.accenture.ra.criteria.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
	name = "_user_", 
	uniqueConstraints = {
		@UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "username"),
	}
)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstname;
	
	@Column(nullable = false)
	private String lastname;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private Integer age;
}