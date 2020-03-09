package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Party;

public interface PartyCrudRepo extends JpaRepository<Party, Long> {
    
}
