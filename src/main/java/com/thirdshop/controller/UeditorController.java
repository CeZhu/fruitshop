package com.thirdshop.controller;

import com.thirdshop.utils.SystemContext;
import com.thirdshop.utils.UUIDUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/ueditor")
public class UeditorController {

    @RequestMapping("/saveFile")
    @ResponseBody
    public Map<String, Object> saveFile(@RequestParam("upfile") MultipartFile file) throws IOException {
        Map<String,Object> params=new HashMap<>();
        String uuid= UUIDUtils.create();
        String path= SystemContext.getRealPath()+"resources\\ueditor\\upload\\"+uuid+file.getOriginalFilename();
        File newFile=new File(path);
        file.transferTo(newFile);
        String visitUrl="/resources/ueditor/upload/"+uuid+file.getOriginalFilename();
        params.put("state","SUCCESS");
        params.put("url",visitUrl);
        params.put("size",file.getSize());
        params.put("original",file.getOriginalFilename());
        params.put("type",file.getContentType());
        return params;
    }
}
