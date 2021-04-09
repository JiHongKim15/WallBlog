package com.web.blog.model.user;

import java.util.HashSet;
import java.util.List;

public class BlogResponse {
	List<Blog> lists;
	HashSet<String> keyword;
	public List<Blog> getLists() {
		return lists;
	}
	public void setLists(List<Blog> lists) {
		this.lists = lists;
	}
	public HashSet<String> getKeyword() {
		return keyword;
	}
	public void setKeyword(HashSet<String> keyword) {
		this.keyword = keyword;
	}
}
