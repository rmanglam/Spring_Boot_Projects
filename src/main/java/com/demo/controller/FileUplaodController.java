package com.demo.controller;

import com.demo.helper.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUplaodController {

@Autowired
    private FileUploadHelper fileUploadHelper;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file) {
        /*System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());*/
try{
        //validation
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
        }
        if (!file.getContentType().equals("image/jpeg")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("only JPEG file allow");
        }

                // FILE UPLOAD CODE
          boolean f=  fileUploadHelper.uploadFile(file);

        if (f){
           // return ResponseEntity.ok("file is uploaded");
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
        }


    }catch (Exception e)
{
    e.printStackTrace();
}
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some thing went wrong, Try again");
    }
}
