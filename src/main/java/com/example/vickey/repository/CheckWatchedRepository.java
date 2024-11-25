package com.example.vickey.repository;

import com.example.vickey.CheckWatched;
import com.example.vickey.CheckWatchedKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckWatchedRepository extends JpaRepository<CheckWatched, CheckWatchedKey> {
}
