package com.web.blog.controller.account;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dao.blog.BlogSearch;
import com.web.blog.model.article.InfoCreateDeleteBean;
import com.web.blog.model.user.Blog;
import com.web.blog.model.user.Like;
import com.web.blog.model.user.Users;
import com.web.blog.service.BoardService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/blog")
public class SearchController {

    @Autowired
    BlogSearch blogSearch;
    
    @Autowired
    BoardService boardService;

    @GetMapping("/search")
    @ApiOperation(value = "키워드와 카테고리에 해당하는 블로그 리스트 검색")
    public ResponseEntity<List<Blog>> search(@RequestParam(value = "keyword") String keyword,
                                             @RequestParam(value = "category", required = false) String category) {

        List<Blog> blogs = blogSearch.selectAll(keyword.trim(), category == null ? null : category.trim());

        if (!(blogs.size() == 0)) {
            return new ResponseEntity<>(blogs, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

        @GetMapping("/detail/{bno}")
    @ApiOperation(value = "해당 블로그 하나 반환")
    public ResponseEntity<Blog> selectOneBlog(@PathVariable("bno") int bno, HttpServletRequest request, HttpServletResponse response) {
        Blog blog = blogSearch.selectOne(bno);

        Cookie[] cookies = request.getCookies();
        Cookie viewCookie = null;
        if(cookies != null && cookies.length > 0){
            for(int i=0; i<cookies.length; i++){
                if(cookies[i].getName().equals("cookies"+bno)){
                    viewCookie = cookies[i];
                }
            }
        }
        if (blog == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        if(viewCookie == null){
            Cookie newCookie = new Cookie("cookie"+bno, "|"+bno+"|");

            response.addCookie(newCookie);
            blogSearch.updateView(bno);
        }
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @GetMapping("/detail/{bno}/like")
    @ApiOperation("좋아요 처리 메소드")
    public ResponseEntity<String> likes(@PathVariable("bno") int bno) {
    		if(blogSearch.updateLike(bno)) {
    			return new ResponseEntity<>("success", HttpStatus.OK);}
    		else
    			return new ResponseEntity<>("false", HttpStatus.FORBIDDEN);
    }
}