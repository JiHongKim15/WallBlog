package com.web.blog.controller.account;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.model.Comment;
import com.web.blog.service.CommentService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/comments")
@Component
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> insert(@Valid @RequestBody Comment comment){
        try {
            commentService.insert(comment);
            return new ResponseEntity<>("Insert Success", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{Cno}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<String> update(@Valid @PathVariable("Cno") Integer Cno,
                                         @RequestBody Comment comment){
        try {
            comment.setCno(Cno);
            commentService.update(comment);
            return new ResponseEntity<>("Update Success", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{Cno}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@Valid @PathVariable("Cno") Integer Cno){
        try {
            commentService.delete(Cno);
            return new ResponseEntity<>("Delete Success", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/all/{Bno}", method = RequestMethod.GET)
    public ResponseEntity<List> listCommentPage(@Valid @PathVariable("Bno") Integer Bno){
        try {
            List<Comment> list = commentService.listCommentPage(Bno);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
