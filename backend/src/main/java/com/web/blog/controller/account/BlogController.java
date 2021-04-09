package com.web.blog.controller.account;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web.blog.dao.blog.BlogDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.user.Blog;
import com.web.blog.model.user.BlogResponse;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogDao blogdao;

	@PostMapping
	@ApiOperation(value = "글쓰기")
	public Object insert(@Valid @RequestBody Blog blog) {

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		blogdao.insert(blog);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping
	@ApiOperation(value = "글삭제")
	public Object delete(@RequestBody Blog blog) {

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		blogdao.delete(blog);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping
	@ApiOperation(value = "글수정")
	public Object update(@Valid @RequestBody Blog blog) {

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		blogdao.update(blog);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/list")
	@ApiOperation(value = "내 글 리스트")
	public Object mylist(@Valid @RequestBody String uid) {
		final BlogResponse response = new BlogResponse();
		List<Blog> lists = blogdao.mylist(uid);

		HashSet<String> key_division = new HashSet<>();

		for (Blog list : lists) {
			key_division.add(list.getKeyword());
		}

		Collections.sort(lists, (a, b) -> a.getKeyword().compareTo(b.getKeyword()));

		response.setLists(lists);
		response.setKeyword(key_division);
		

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
}
