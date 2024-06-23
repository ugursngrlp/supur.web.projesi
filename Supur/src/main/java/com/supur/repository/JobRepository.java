package com.supur.repository;

import com.supur.entity.Category;
import com.supur.entity.Job;
import com.supur.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByCategory(Category category);

    List<Job> findByUser(User user);
}
