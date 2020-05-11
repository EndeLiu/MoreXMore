package com.more.demo.controller;


import com.more.demo.Config;
import com.more.demo.pojo.CustomResult;
import com.more.demo.utils.HttpUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
public class LoginController {






    @GetMapping("/api/login")
    public CustomResult Login(String code) throws IOException {
        Config config = new Config();//app id&secret的配置
        //GET https://api.weixin.qq.com/sns/jscode2session   ?appid=APPID & secret=SECRET & js_code=JSCODE  & grant_type=authorization_code
        System.out.println(code);
        String urlHeader = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> urlParams = new HashMap<>();
        urlParams.put("appid", config.getAPPID());
        urlParams.put("secret", config.getAPP_SECRET());
        urlParams.put("js_code", code);
        urlParams.put("grant_type", "authorization_code");
        String httpResult = HttpUtils.getResponse(urlHeader,urlParams);

        return new CustomResult(httpResult);
    }

}
