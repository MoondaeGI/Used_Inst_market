package com.example.Used_Inst_market.util;

import com.example.Used_Inst_market.model.domain.board.picture.Picture;
import com.example.Used_Inst_market.model.domain.board.picture.PictureRepository;
import com.example.Used_Inst_market.model.domain.board.post.Post;
import com.example.Used_Inst_market.model.domain.board.post.PostRepository;
import com.example.Used_Inst_market.model.vo.board.PictureVO;
import com.example.Used_Inst_market.util.filehandler.FileHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileHandlerTest {
    @Autowired private PostRepository postRepository;
    @Autowired private PictureRepository pictureRepository;
    @Autowired private FileHandler fileHandler;

    @Test
    public void file_mkdir_경로검증() {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyyMMdd");
        String testCurrentDate = simpleDateFormat.format(new Date());

        String filePath = "image/" + testCurrentDate;
        File testFile = new File(filePath);

        testFile.mkdir();

        assertTrue(testFile.exists());
    }

    @Test
    public void pictureFileToByte_검증() throws IOException {
        Post post = postRepository.findById(18L)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));

        List<PictureVO> pictures = pictureRepository
                .findByPost(post).stream()
                .map(PictureVO::from)
                .collect(Collectors.toList());
        List<byte[]> byteList = fileHandler.imageToByteArray(pictures);

        for (byte[] byteArray : byteList) {
            System.out.println(Arrays.toString(byteArray));
        }
    }
}
