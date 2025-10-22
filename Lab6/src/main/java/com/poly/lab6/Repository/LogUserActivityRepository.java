package com.poly.Lab6.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.Lab6.Entity.LogUserActivities;

public interface LogUserActivityRepository extends JpaRepository<LogUserActivities, Integer> {
    // Find all activities for a specific user
    List<LogUserActivities> findByUserUserId(Integer userId);

    // Find by token
    LogUserActivities findByToken(String token);
}
