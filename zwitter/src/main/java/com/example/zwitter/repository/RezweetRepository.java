package com.example.zwitter.repository;

import com.example.zwitter.entity.Rezweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RezweetRepository extends JpaRepository<Rezweet, Long> {
    List<Rezweet> findByUserId(Long userId);
    List<Rezweet> findByZweetId(Long zweetId);
    Optional<Rezweet> findByUserIdAndZweetId(Long userId, Long zweetId);
    boolean existsByUserIdAndZweetId(Long userId, Long zweetId);
} 