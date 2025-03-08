package com.example.zwitter.controller;

import com.example.zwitter.dto.ZweetRecord;
import com.example.zwitter.entity.Zweet;
import com.example.zwitter.service.ZweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
        method  url
        GET     /zweet/{userId} --> user'a ait tüm zweetleri getiricek.
        GET     /zweet/{id} --> zweet'in tüm bilgilerini getiricek.
        POST    /zweet      -> save
        DELETE  /zweet/{id} --> id'deki zweet'i silecek.
        PUT     /zweet/{id} -> update content
*/

@RestController
@RequestMapping("/zweets")
public class ZweetController {
    private ZweetService zweetService;

    @Autowired
    public ZweetController(ZweetService zweetService) {
        this.zweetService = zweetService;
    }

    @GetMapping("/")
    public List<ZweetRecord> getAllZweets() {
        return zweetService.getAllZweets();
    }

    @GetMapping("/user/{userId}")
    public List<ZweetRecord> zweetsByUserId(@PathVariable Long userId) {
        return zweetService.ZweetsByUserId(userId);
    }

    @GetMapping("/id/{id}")
    public ZweetRecord zweetById(@PathVariable Long id){
        return zweetService.getZweetById(id);
    }

    @PostMapping("/create")
    public ZweetRecord create(@RequestBody String content){
        return zweetService.create(content);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        zweetService.deleteZweet(id);
    }

    @PutMapping("/update/{id}")
    public ZweetRecord updateZweet(@PathVariable Long id, @RequestBody Zweet zweet) {
        return zweetService.updateZweet(id, zweet);
    }

}
