package com.example.zwitter.service;

import com.example.zwitter.dto.ZweetRecord;
import com.example.zwitter.entity.Zweet;
import java.util.List;

public interface ZweetService {
    List<ZweetRecord> getAllZweets();
    ZweetRecord create(String content);
    ZweetRecord getZweetById(Long id);
    List<ZweetRecord> ZweetsByUserId(Long id);
    ZweetRecord updateZweet(Long id, Zweet zweet);
    void deleteZweet(Long id);
} 