package com.thoughtworks.gtb.bbasicquiz.repository;

import com.thoughtworks.gtb.bbasicquiz.domain.Education;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRepository {
    Education save(Education education);

    List<Education> findAllByUserId(long userId);
}
