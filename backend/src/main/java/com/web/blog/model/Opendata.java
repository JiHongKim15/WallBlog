package com.web.blog.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Opendata {
	@Id
	private String companyname;
    private String contents;
    private String createdDate;
    private String lastModifiedUid;
    private String lastModifiedDate;
	
}
