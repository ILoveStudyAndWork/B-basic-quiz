package com.thoughtworks.gtb.bbasicquiz.repository;

import com.thoughtworks.gtb.bbasicquiz.domain.Education;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EducationRepositoryImpl implements EducationRepository{

    // 改成final
    private static final List<Education> educations = new ArrayList<>();

    @Override
    public Education save(Education education) {
        educations.add(education);
        return education;
    }

    @Override
    public List<Education> findAllByUserId(long userId) {
        return educations.stream()
                .filter(education -> userId == education.getUserId())
                .collect(Collectors.toList());
    }
}
