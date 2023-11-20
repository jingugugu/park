package com.example.smartparkpj.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
@Log4j2
public class adminControllerTests {
    @Value("${com.example.upload.tempPath}")
    private String uploadTempPath;
    @Value("${com.example.upload.AttractionPath}")
    private String uploadAttractionPath;

    @Test
    public void checkFileTest() {
        File folder = new File(uploadTempPath);

        File[] imageList = folder.listFiles();

        if (imageList != null){
            for (File file : imageList){
                log.info(file.getName());
            }
        }
    }

    @Test
    public void copyFileTest(){

        String imgName = "b9eb19a3-fc81-44b5-88e7-916b9cba7683_Renoir11.jpg";

        try{
            // 읽을 파일과 쓰기 파일을 구분해서 객체 생성.
            FileInputStream fileInputStream = new FileInputStream(uploadTempPath + File.separator + imgName);
            FileOutputStream fileOutputStream = new FileOutputStream(uploadAttractionPath + File.separator + imgName);

            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            int i;

            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);

            }
            bufferedInputStream.close();
            bufferedOutputStream.close();
            fileInputStream.close();
            fileOutputStream.close();
            Files.delete(Paths.get(uploadTempPath + File.separator + imgName));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
