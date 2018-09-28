package com.chen.web.controller;

import com.chen.web.common.ConstantUtil;
import com.chen.web.entity.FileInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件处理
 */
@RequestMapping("/file")
public class FileController {

    /**
     * 文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public FileInfo upload(MultipartFile file) throws IOException {

        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        InputStream in = file.getInputStream();
        File localFile = new File(ConstantUtil.FILE_PATH,file.getOriginalFilename());
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }
    @GetMapping("/{id}")
    public void downLoad(@PathVariable String id,
                         HttpServletRequest request, HttpServletResponse response){

    }

}
