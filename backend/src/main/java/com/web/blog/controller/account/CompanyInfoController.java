package com.web.blog.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.model.article.CompanyInfo;
import com.web.blog.model.article.InfoCreateDeleteBean;
import com.web.blog.service.CompanyInfoService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/companyinfo")
public class CompanyInfoController {

    @Autowired
    private CompanyInfoService service;

    @PostMapping("/new")
    @ApiOperation("CompanyInfo에 대한 CompanyName의 데이터 공간 생성")
    public ResponseEntity<String> createCompanyInfo(@RequestBody InfoCreateDeleteBean infoCreateDeleteBean) {

        if (service.selectOfficial(infoCreateDeleteBean.getCompanyName()) != null)
            return new ResponseEntity<>("이미 있는 기업입니다.", HttpStatus.METHOD_NOT_ALLOWED);

        if (service.createCompanyInfo(infoCreateDeleteBean)) {
            return new ResponseEntity<>("success", HttpStatus.OK);
        } else
            return new ResponseEntity<>("권한이 없습니다.", HttpStatus.FORBIDDEN);
    }

    @GetMapping("/official")
    @ApiOperation("companyName에 해당하는 official CompanyInfo를 반환합니다.")
    public ResponseEntity<CompanyInfo> selectOfficial(@RequestParam("companyName") String companyName) {
        CompanyInfo companyInfo = service.selectOfficial(companyName);
        if (companyInfo == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(companyInfo, HttpStatus.OK);
    }

    @GetMapping("/unofficial")
    @ApiOperation("companyName에 해당하는 unofficial CompanyInfo를 반환합니다.")
    public ResponseEntity<CompanyInfo> selectUnofficial(@RequestParam("companyName") String companyName) {
        CompanyInfo companyInfo = service.selectUnofficial(companyName);

        if (companyInfo == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(companyInfo, HttpStatus.OK);
    }

    @PutMapping("/official")
    @ApiOperation("companyName에 해당하는 official CompanyInfo를 수정합니다.")
    public ResponseEntity<String> updateOfficial(@RequestBody CompanyInfo modifiedData) {

        CompanyInfo storedData = service.selectOfficial(modifiedData.getCompanyName());
        boolean check;

        if (storedData.getLastModifiedUid().equals(modifiedData.getLastModifiedUid()) && storedData.getLastModifiedDate().equals(modifiedData.getLastModifiedDate())) {
            check = service.updateOfficial(modifiedData);
        } else {
            return new ResponseEntity<>("이미 다른 사람이 수정한 문서입니다. 새로고침해주세요.", HttpStatus.FORBIDDEN);
        }

        if (check) {
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        return new ResponseEntity<>("권한이 없습니다.", HttpStatus.FORBIDDEN);
    }

    @PutMapping("/summary")
    @ApiOperation("해당 기업의 summary 정보를 수정합니다.")
    public ResponseEntity<String> updateSummary(@RequestBody CompanyInfo modifiedData) {

        if (modifiedData == null || !modifiedData.getCurrentUid().equals("admin"))
            return new ResponseEntity<>("관리자 권한이 없습니다.", HttpStatus.FORBIDDEN);
        else if (service.selectOfficial(modifiedData.getCompanyName()) == null)
            return new ResponseEntity<>("기업 페이지가 없습니다. 기업 페이지를 다시 확인해주세요.", HttpStatus.NO_CONTENT);

        boolean check;
        check = service.updateSummary(modifiedData);

        return check ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>("데이터베이스 자료 수정 중 오류 발생", HttpStatus.CONFLICT);
    }

    @PutMapping("/unofficial")
    @ApiOperation("companyName에 해당하는 unofficial CompanyInfo를 수정합니다.")
    public ResponseEntity<String> updateUnofficial(@RequestBody CompanyInfo modifiedData) {

        CompanyInfo storedData = service.selectUnofficial(modifiedData.getCompanyName());
        boolean check;

        if (storedData.getLastModifiedUid().equals(modifiedData.getLastModifiedUid()) && storedData.getLastModifiedDate().equals(modifiedData.getLastModifiedDate())) {
            check = service.updateUnofficial(modifiedData);
        } else {
            return new ResponseEntity<>("이미 다른 사람이 수정한 문서입니다. 새로고침해주세요.", HttpStatus.FORBIDDEN);
        }

        if (check) {
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        return new ResponseEntity<>("fail", HttpStatus.CONFLICT);
    }

    @DeleteMapping("/delete")
    @ApiOperation("CompanyInfo에 대한 CompanyName의 데이터 공간 제거")
    public ResponseEntity<String> deleteCompanyInfo(@RequestBody InfoCreateDeleteBean infoCreateDeleteBean) {

        if (service.selectOfficial(infoCreateDeleteBean.getCompanyName()) != null) {
            if (service.deleteCompanyInfo(infoCreateDeleteBean))
                return new ResponseEntity<>("success", HttpStatus.OK);
            return new ResponseEntity<>("권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("fail", HttpStatus.NO_CONTENT);
    }

}