package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/session")
public class SessionController {

    // HttpSession型のフィールドを定義する
    private HttpSession session;

    // コンストラクタを作成し、@Autowiredアノテーションを付与する
    @Autowired
    public SessionController(HttpSession session) {
        // フィールドに代入する
        this.session = session;
    }
    
    @GetMapping("/set")
    @ResponseBody
    public String set(@RequestParam("name") String name, @RequestParam("bloodType") String bloodType) {
        // Sessionへの保存
        this.session.setAttribute("name", name);
        this.session.setAttribute("bloodType", bloodType);
        return "保存しました";
    }

    @GetMapping("/get")
    @ResponseBody
    public String get() {
        String name = (String) this.session.getAttribute("name");
        String bloodType = (String) this.session.getAttribute("bloodType");
        if (name == null) {
            name = "名無し";
        }
        if (bloodType == null) {
            bloodType = "不明";
        }
        return "名前: " + name + "<br>血液型: " + bloodType;
    }
    
    @GetMapping("/delete")
    @ResponseBody
    public String delete() {
        // Session名を指定して削除
        session.removeAttribute("name");
        session.removeAttribute("bloodType");
        return "削除しました";
    }
}