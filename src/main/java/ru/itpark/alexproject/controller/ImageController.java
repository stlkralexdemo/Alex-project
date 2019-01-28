package ru.itpark.alexproject.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Controller
@RequestMapping("/image")
public class ImageController {


    @GetMapping("/{imageName}")
    public MultipartFile getImage(@PathVariable("imageName") String imageName) throws FileNotFoundException {
        return IOUtils(new FileOutputStream("files/" + imageName));
        return new Mu
    }

}
