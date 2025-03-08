package com.example.zwitter.service;

import com.example.zwitter.dto.RezweetRecord;
import com.example.zwitter.entity.Zweet;
import com.example.zwitter.user.User;

import java.util.List;

public interface RezweetService {
    RezweetRecord rezweet(Long zweetId);
    void undoRezweet(Long zweetId);
    List<RezweetRecord> getRezweetsByUser(Long userId);
    List<RezweetRecord> getRezweetsByZweet(Long zweetId);
}