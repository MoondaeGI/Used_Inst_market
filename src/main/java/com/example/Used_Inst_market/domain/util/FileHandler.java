package com.example.Used_Inst_market.domain.util;

import com.example.Used_Inst_market.domain.board.picture.Picture;
import com.example.Used_Inst_market.domain.board.post.Post;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FileHandler {
    public List<Picture> parsePictureFileInfo(
            Post post, List<MultipartFile> multipartFiles)
            throws IOException {
        List<Picture> pictureList = new ArrayList<>();

        if (multipartFiles.isEmpty()) {
            return pictureList;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDate = simpleDateFormat.format(new Date());

        String path = "/images" + currentDate;
        String absolutePath = new File("").getAbsolutePath() + "\\";

        File file = new File(path);
        if (!file.exists()) {
            try {
                file.mkdir();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                String contentType = multipartFile.getContentType();
                String originalFileException;

                if (ObjectUtils.isEmpty(contentType)) {
                    break;
                } else {
                    if (contentType.contains("image/jpeg")) {
                        originalFileException = ".jpg";
                    } else if (contentType.contains("image/png")) {
                        originalFileException = ".png";
                    } else {
                        break;
                    }
                }
                String name = System.nanoTime() + originalFileException;

                Picture picture = Picture.builder()
                        .post(post)
                        .originalFileName(multipartFile.getOriginalFilename())
                        .name(name)
                        .path(path + "/" + name)
                        .size(multipartFile.getSize())
                        .build();

                pictureList.add(picture);

                file = new File(absolutePath + path + "/" + name);
                multipartFile.transferTo(file);
            }
        }

        return pictureList;
    }
}
