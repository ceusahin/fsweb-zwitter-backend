package com.example.zwitter.controller;

import com.example.zwitter.dto.RezweetRecord;
import com.example.zwitter.service.RezweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rezweets")
public class RezweetController {
    private RezweetService rezweetService;

    @Autowired
    public RezweetController(RezweetService rezweetService) {
        this.rezweetService = rezweetService;
    }

    @GetMapping("/zweets/{id}")
    public List<RezweetRecord> getRezweetsByZweet(@PathVariable Long id) {
        return rezweetService.getRezweetsByZweet(id);
    }

    @GetMapping("/zweets/user/{id}")
    public List<RezweetRecord> getRezweetsByUser(@PathVariable Long userId) {
        return rezweetService.getRezweetsByUser(userId);
    }

    @PostMapping("/{zweetId}")
    public RezweetRecord rezweet(@PathVariable Long zweetId) {
        return rezweetService.rezweet(zweetId);
    }

    @DeleteMapping("/undo/{zweetId}")
    public void undoRezweet(@PathVariable Long zweetId) {
        rezweetService.undoRezweet(zweetId);
    }
}
