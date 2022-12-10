package com.example.Used_Inst_market.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileHandlerTest {
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
}
