package com.web.blog.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

	@Id
	int bno;
	String keyword;
	String title;
	String contents;
	String uid;
	int likes;
	int views;
	String create_date;
	String category;
}
