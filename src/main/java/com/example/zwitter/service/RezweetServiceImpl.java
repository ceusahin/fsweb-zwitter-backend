package com.example.zwitter.service;

import com.example.zwitter.dto.RezweetRecord;
import com.example.zwitter.dto.ZweetRecord;
import com.example.zwitter.entity.Rezweet;
import com.example.zwitter.entity.Zweet;
import com.example.zwitter.exceptions.NotFoundException;
import com.example.zwitter.repository.RezweetRepository;

import com.example.zwitter.repository.UserRepository;
import com.example.zwitter.repository.ZweetRepository;
import com.example.zwitter.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RezweetServiceImpl implements RezweetService {
    
    private RezweetRepository rezweetRepository;
    private UserRepository userRepository;
    private ZweetRepository zweetRepository;

    @Autowired
    public RezweetServiceImpl(RezweetRepository rezweetRepository, UserRepository userRepository, ZweetRepository zweetRepository) {
        this.rezweetRepository = rezweetRepository;
        this.userRepository = userRepository;
        this.zweetRepository = zweetRepository;
    }


    @Override
    public RezweetRecord rezweet(Long zweetId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findUserByUserName(username).orElseThrow(() -> new NotFoundException("User not found!"));

        if (rezweetRepository.existsByUserIdAndZweetId(user.getId(), zweetId)) {
            throw new IllegalArgumentException("User has already rezweeted this zweet.");
        }

        Zweet zweet = zweetRepository.findById(zweetId)
                .orElseThrow(() -> new IllegalArgumentException("Zweet not found with id: " + zweetId));

        Rezweet rezweet = new Rezweet();
        rezweet.setUser(user);
        rezweet.setZweet(zweet);
        rezweet.setRezweetedAt(LocalDateTime.now());

        rezweetRepository.save(rezweet);
        return new RezweetRecord(rezweet.getId(), rezweet.getUser().getUsername(), rezweet.getZweet().getContent(), rezweet.getRezweetedAt());
    }

    @Override
    public void undoRezweet(Long zweetId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findUserByUserName(username).orElseThrow(() -> new NotFoundException("User not found!"));

        Optional<Rezweet> optionalRezweet = rezweetRepository.findByUserIdAndZweetId(user.getId(), zweetId);
        if (optionalRezweet.isPresent()) {
            Rezweet rezweet = optionalRezweet.get();
            rezweetRepository.delete(rezweet);
        }
    }

    @Override
    public List<RezweetRecord> getRezweetsByUser(Long userId) {
        List<Rezweet> rezweets = rezweetRepository.findByUserId(userId);
        return rezweets.stream().map(rezweet -> new RezweetRecord(
                rezweet.getId(),
                rezweet.getUser().getUsername(),
                rezweet.getZweet().getContent(),
                rezweet.getRezweetedAt()
        )).toList();
    }

    @Override
    public List<RezweetRecord> getRezweetsByZweet(Long id) {
        List<Rezweet> rezweets = rezweetRepository.findByZweetId(id);
        return rezweets.stream().map(rezweet -> new RezweetRecord(
                rezweet.getId(),
                rezweet.getUser().getUsername(),
                rezweet.getZweet().getContent(),
                rezweet.getRezweetedAt()
        )).toList();
    }
}