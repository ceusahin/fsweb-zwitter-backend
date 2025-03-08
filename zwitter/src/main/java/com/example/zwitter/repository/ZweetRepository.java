package com.example.zwitter.repository;

import com.example.zwitter.entity.Zweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZweetRepository extends JpaRepository<Zweet, Long> {
    @Query("SELECT z FROM Zweet z WHERE z.user.id = :id")
    List<Zweet> ZweetsByUserId(@Param("id") Long id);
} 