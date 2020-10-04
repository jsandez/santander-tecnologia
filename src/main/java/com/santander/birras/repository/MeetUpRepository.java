package com.santander.birras.repository;

import com.santander.birras.model.MeetUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeetUpRepository extends JpaRepository<MeetUp,Long> {
    @Override
    Optional<MeetUp> findById(Long aLong);
}
