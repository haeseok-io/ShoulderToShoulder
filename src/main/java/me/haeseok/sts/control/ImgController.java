package me.haeseok.sts.control;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Controller
public class ImgController {
    @Value("${me.haeseok.sts.upload.directory.moim}")
    private String moimUploadPath;

    @Value("${me.haeseok.sts.upload.directory.user}")
    private String userUploadPath;

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName, @RequestParam(required = false) String directory) {
        ResponseEntity<byte[]> result;

        try {
            HttpHeaders header = new HttpHeaders();
            String srcFileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8);
            File file = null;

            if( directory==null || directory.equals("moim") ) {
                file = new File(moimUploadPath+File.separator + srcFileName);
            } else if( directory.equals("user") ) {
                file = new File(userUploadPath+File.separator + srcFileName);
            }

            // 파일이 존재하는지 확인 후 처리
            if (!file.exists()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 파일을 찾지 못하면 404 반환
            }

            // Mime 타입 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));

            // 파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }
}
