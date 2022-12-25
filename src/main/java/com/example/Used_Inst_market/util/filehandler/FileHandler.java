package com.example.Used_Inst_market.util.filehandler;

import com.example.Used_Inst_market.model.domain.board.picture.Picture;
import com.example.Used_Inst_market.model.domain.board.post.Post;
import com.example.Used_Inst_market.model.vo.board.PictureVO;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FileHandler {
    public List<Picture> parseImageFileInfo(
            Post post, List<MultipartFile> multipartFiles) throws IOException {
        List<Picture> pictureList = new ArrayList<>();

        if (multipartFiles.isEmpty()) {
            System.out.println("list 없음");
            return pictureList;
        }

        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyyMMdd");
        String currentDate = simpleDateFormat.format(new Date());

        String path = "images/" + currentDate;
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
                    if (contentType.contains("image/jpeg")
                            || contentType.contains("image/jpg")) {
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

    public byte[] imageToByteArray(Picture picture) throws IOException {
        String path = picture.getPath();
        InputStream imageStream = Files.newInputStream(Paths.get(path));

        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();

        return imageByteArray;
    }

    public void deleteImageFile(List<Picture> pictures) throws IOException {
        for (Picture picture : pictures) {
            String imagePath = picture.getPath();
            Path filePath = Paths.get("images/" + imagePath);

            if(!Files.exists(filePath)) {
                Files.delete(filePath);
            }
        }
    }
}
