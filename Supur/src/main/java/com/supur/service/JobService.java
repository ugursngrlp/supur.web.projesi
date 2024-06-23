package com.supur.service;

import com.supur.entity.Category;
import com.supur.entity.Job;
import com.supur.entity.User;
import com.supur.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> findByCategory(Category category) {
        if (Category.TUMU.equals(category)) {
            return jobRepository.findAll();
        }
        return jobRepository.findByCategory(category);
    }

    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public List<Job> getJobsByUser(User user) {
        return jobRepository.findByUser(user);
    }
}