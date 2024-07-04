package me.haeseok.sts.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileUploadService {
    public Map<String, String> uploadFile(MultipartFile file, String directory) throws IOException {
        // 파일이 없는 경우
        if( file.isEmpty() ) {
            throw new IOException("업로드 할 파일이 존재하지 않습니다.");
        }

        Path uploadDirectory = Paths.get(directory);
        String uploadFileName = getUniqueFileName(file.getOriginalFilename());

        // 최종 저장될 파일 Path
        Path targetFilePath = uploadDirectory.resolve(Paths.get(uploadFileName))
                .normalize()
                .toAbsolutePath();

        // 저장될 파일 경로가 기본 디렉토리를 벗어나진 않는지 체크
        if( !targetFilePath.getParent().equals(uploadDirectory.toAbsolutePath()) ) {
            throw new IOException("저장될 디렉토리 경로를 확인해주세요.");
        }

        // 디렉토리가 존재하지 않을경우 생성
        Files.createDirectories(uploadDirectory);

        // 파일 업로드
        try (var inputStream = file.getInputStream()) {
            Files.copy(inputStream, targetFilePath);
        }

        // 원본파일명과 업로드 파일명 반환
        Map<String, String> result = new HashMap<>();
        result.put("uploadFileName", uploadFileName);
        result.put("originalFileName", file.getOriginalFilename());
        return result;
    }


    // 현재 타임스탬프 값을 반환
    private String getUniqueFileName(String filename) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // 파일 확장자 추출
        String[] fileInfo = filename.split("\\.");
        String extension = fileInfo.length > 1 ? "."+fileInfo[fileInfo.length-1] : "";

        return timestamp.getTime()+extension;
    }
}
