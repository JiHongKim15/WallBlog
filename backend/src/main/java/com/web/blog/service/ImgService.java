package com.web.blog.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImgService {

    String saveProfileImg(MultipartFile multipartFile);

    byte[] loadProfileImg(String profileImgName) throws IOException;

}