package com.example.zwitter.service;

import com.example.zwitter.dto.ZweetRecord;
import com.example.zwitter.user.User;
import com.example.zwitter.entity.Zweet;
import com.example.zwitter.exceptions.NotFoundException;
import com.example.zwitter.repository.UserRepository;
import com.example.zwitter.repository.ZweetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZweetServiceImpl implements ZweetService {
    
    private ZweetRepository zweetRepository;
    private UserRepository userRepository;

    @Autowired
    public ZweetServiceImpl(ZweetRepository zweetRepository, UserRepository userRepository) {
        this.zweetRepository = zweetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ZweetRecord> getAllZweets() {
        List<Zweet> zweets = zweetRepository.findAll();
        return zweets.stream().map(zweet -> new ZweetRecord(
                zweet.getId(),
                zweet.getUser().getUsername(),
                zweet.getContent(),
                zweet.getCreatedAt()
        )).toList();
    }

    @Override
    public ZweetRecord create(String content) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user = userRepository.findUserByUserName(userName).orElseThrow(() -> new NotFoundException("User not found!"));

        Zweet zweet = new Zweet();
        zweet.setContent(content);
        zweet.setUser(user);

        zweetRepository.save(zweet);

        return new ZweetRecord(
                zweet.getId(),
                zweet.getUser().getUsername(),
                zweet.getContent(),
                zweet.getCreatedAt()
        );
    }

    @Override
    public ZweetRecord getZweetById(Long id) {
        Zweet zweet = zweetRepository.findById(id).orElseThrow(() -> new NotFoundException("Zweet not found"));
        return new ZweetRecord(
                zweet.getId(),
                zweet.getUser().getUsername(),
                zweet.getContent(),
                zweet.getCreatedAt()
        );
    }

    @Override
    public List<ZweetRecord> ZweetsByUserId(Long id) {
        List<Zweet> zweets = zweetRepository.ZweetsByUserId(id);
        return zweets.stream().map(zweet -> new ZweetRecord(
                zweet.getId(),
                zweet.getUser().getUsername(),
                zweet.getContent(),
                zweet.getCreatedAt()
        )).toList();
    }

    @Override
    public ZweetRecord updateZweet(Long id, Zweet zweet) {
        Zweet zweetToUpdate = zweetRepository.findById(id).orElseThrow(() -> new NotFoundException("Can not found zweet with this id: " + id));

        if (zweet.getContent() != null) {
            zweetToUpdate.setContent(zweet.getContent());
        }
        zweetRepository.save(zweetToUpdate);

        return new ZweetRecord(
                zweetToUpdate.getId(),
                zweetToUpdate.getUser().getUsername(),
                zweetToUpdate.getContent(),
                zweetToUpdate.getCreatedAt()
        );
    }

    @Override
    public void deleteZweet(Long id) {
        zweetRepository.deleteById(id);
    }
}