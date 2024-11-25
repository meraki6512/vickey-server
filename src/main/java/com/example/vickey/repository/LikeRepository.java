package com.example.vickey.repository;

import com.example.vickey.LikeKey;
import com.example.vickey.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeKey> {
}
