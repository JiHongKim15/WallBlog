package com.web.blog.service;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service("ImgServiceImpl")
public class ImgServiceImpl implements ImgService {

    @Override
    public String saveProfileImg(MultipartFile multipartFile) {
        String newFileName;
        final String path = getClass().getResource("/profile").getPath();
        System.out.println(path);
        try {
            UUID uuid = UUID.randomUUID();
            newFileName = uuid.toString() + '_' + multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(path + "/" + newFileName));

        } catch (IllegalStateException | IOException e) {
            System.out.println("===== 파일 저장 중 오류 발생 =====");
            return null;
        }
        return newFileName;
    }

    @Override
    public byte[] loadProfileImg(String profileImgName) throws IOException {
        InputStream in;

        in = getClass().getResourceAsStream("/profile/" + profileImgName);
        if(in == null )
            in = getClass().getResourceAsStream("/profile/기본.jpg");

        return IOUtils.toByteArray(in);
    }

}