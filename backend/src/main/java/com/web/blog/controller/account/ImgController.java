package com.web.blog.controller.account;

import com.web.blog.service.ImgService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/img")
public class ImgController {

    @Autowired
    ImgService imgService;

    @GetMapping(produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    @ApiOperation("동일한 이름의 이미지 파일을 반환")
    public ResponseEntity<Object> loadProfileImg(@RequestParam(value = "profileImgName") String profileImgName) {
    	byte[] img;
        try {
            img = imgService.loadProfileImg(profileImgName);
        } catch (IOException e) {
            return new ResponseEntity<>("fail", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(img, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("이미지를 src/main/resources/profile/에 저장")
    public ResponseEntity<Object> saveProfileImg(@RequestBody MultipartFile profileImg) {
        String newFileName = imgService.saveProfileImg(profileImg);
        
        // src/main/resources/profile
        if (newFileName == null)
            return new ResponseEntity<>("이미지 저장 실패", HttpStatus.CONFLICT);
        return new ResponseEntity<>(newFileName, HttpStatus.OK);
    }

}