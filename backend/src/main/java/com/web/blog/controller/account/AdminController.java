package com.web.blog.controller.account;

import com.web.blog.model.article.CategoryAdminCheck;
import com.web.blog.model.article.CompanyInfo;
import com.web.blog.model.article.InfoCreateDeleteBean;
import com.web.blog.model.user.Blog;
import com.web.blog.model.user.UserUsedByAdmin;
import com.web.blog.service.AdminPageService;
import com.web.blog.service.CompanyInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminPageService adminPageService;
    
    @Autowired
    CompanyInfoService companyInfoService;

    @GetMapping("/company")
    @ApiOperation("관리에 필요한 기업들의 정보 리스트를 반환")
    ResponseEntity<Object> companyList(@RequestParam(value = "companyName", required = false) String companyName) {
        List<CompanyInfo> list;

        if (companyName == null)
            list = adminPageService.companyList(null);
        else
            list = adminPageService.companyList(companyName);

        if (list.isEmpty())
            return new ResponseEntity<>("해당하는 검색 결과가 없습니다.", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/company")
    @ApiOperation("CompanyInfo에 대한 CompanyName의 데이터 공간 생성")
    public ResponseEntity<String> createCompanyInfo(@RequestBody(required = false) InfoCreateDeleteBean infoCreateDeleteBean) {
        if (infoCreateDeleteBean.getUid() == null || !infoCreateDeleteBean.getUid().equals("admin"))
            return new ResponseEntity<>("권한이 없습니다.", HttpStatus.FORBIDDEN);

        if (companyInfoService.selectOfficial(infoCreateDeleteBean.getCompanyName()) != null)
            return new ResponseEntity<>("이미 있는 기업입니다.", HttpStatus.METHOD_NOT_ALLOWED);
        else {
            if (companyInfoService.createCompanyInfo(infoCreateDeleteBean))
                return new ResponseEntity<>("success", HttpStatus.OK);
            else
                return new ResponseEntity<>("데이터 생성 중 오류", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/company")
    @ApiOperation("CompanyInfo에 대한 CompanyName의 데이터 공간 제거")
    public ResponseEntity<String> deleteCompanyInfo(@RequestBody(required = false) InfoCreateDeleteBean infoCreateDeleteBean) {
        if (infoCreateDeleteBean.getUid() == null || !infoCreateDeleteBean.getUid().equals("admin"))
            return new ResponseEntity<>("권한이 없습니다.", HttpStatus.FORBIDDEN);

        if (companyInfoService.selectOfficial(infoCreateDeleteBean.getCompanyName()) != null) {
            if (companyInfoService.deleteCompanyInfo(infoCreateDeleteBean))
                return new ResponseEntity<>("success", HttpStatus.OK);
            return new ResponseEntity<>("데이터 삭제 중 오류 발생", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("등록되지 않은 기업명입니다.", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user")
    @ApiOperation("관리에 필요한 유저 리스트를 반환")
    ResponseEntity<Object> userList(@RequestParam(value = "uid", required = false) String uid) {
        List<UserUsedByAdmin> list;

        if (uid == null)
            list = adminPageService.userList(null);
        else
            list = adminPageService.userList(uid);

        if (list.isEmpty())
            return new ResponseEntity<>("해당하는 검색 결과가 없습니다.", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/blog")
    @ApiOperation("특정 유저의 블로그글 검색")
    ResponseEntity<Object> blogList(@RequestParam(value = "uid", required = false) String uid) {
        if (uid == null)
            return new ResponseEntity<>("회원아이디를 입력해주세요.", HttpStatus.BAD_REQUEST);

        List<Blog> list;

        if (adminPageService.isUser(uid)) {
            list = adminPageService.blogList(uid);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("존재하지 않는 회원 아이디입니다.", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/category")
    @ApiOperation("블로그의 카테고리 리스트 반환")
    ResponseEntity<Object> categoryList() {
        List<String> list = adminPageService.categoryList();

        if (list.isEmpty())
            return new ResponseEntity<>("생성된 카테고리가 없습니다.", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/category")
    @ApiOperation("블로그 카테고리 생성")
    ResponseEntity<Object> createCategory(@RequestBody(required = false) CategoryAdminCheck categoryAdminCheck) {
        if (categoryAdminCheck == null || categoryAdminCheck.getUid() == null || !categoryAdminCheck.getUid().equals("admin"))
            return new ResponseEntity<>("권한이 없습니다.", HttpStatus.FORBIDDEN);

        List<String> list = adminPageService.categoryList();
        if (list.contains(categoryAdminCheck.getCategory()))
            return new ResponseEntity<>("이미 있는 카테고리 입니다.", HttpStatus.METHOD_NOT_ALLOWED);

        if (adminPageService.createCategory(categoryAdminCheck.getCategory()))
            return new ResponseEntity<>("success", HttpStatus.OK);
        return new ResponseEntity<>("카테고리 생성에 실패했습니다.", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/category")
    @ApiOperation("블로그 카테고리 삭제")
    ResponseEntity<Object> deleteCategory(@RequestBody(required = false) CategoryAdminCheck categoryAdminCheck) {
        if (categoryAdminCheck == null || categoryAdminCheck.getUid() == null || !categoryAdminCheck.getUid().equals("admin"))
            return new ResponseEntity<>("권한이 없습니다.", HttpStatus.FORBIDDEN);

        List<String> list = adminPageService.categoryList();
        if (!list.contains(categoryAdminCheck.getCategory()))
            return new ResponseEntity<>("존재하지 않는 카테고리입니다", HttpStatus.NOT_FOUND);

        if (adminPageService.deleteCategory(categoryAdminCheck.getCategory()))
            return new ResponseEntity<>("카테고리 삭제 성공", HttpStatus.OK);
        return new ResponseEntity<>("카테고리 삭제에 실패했습니다.", HttpStatus.CONFLICT);
    }

}