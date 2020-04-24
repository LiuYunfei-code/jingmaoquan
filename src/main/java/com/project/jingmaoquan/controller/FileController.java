package com.project.jingmaoquan.controller;

import com.project.jingmaoquan.provider.COSProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileController {
    final Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private COSProvider cosProvider;

    @RequestMapping("/file/upload")
    @ResponseBody
    public Map<String,String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file==null) {
            logger.info("上传失败");
        }

        logger.info("成功");
        File tempFile=File.createTempFile("prefix","suffix");
        if (file != null) {
            file.transferTo(tempFile);
        }
        String url=cosProvider.COSUpload(tempFile,file.getOriginalFilename());
        Map<String,String> map=new HashMap<>();
        map.put("uploaded","true");
        map.put("url",url);
        return map;
    }
}
