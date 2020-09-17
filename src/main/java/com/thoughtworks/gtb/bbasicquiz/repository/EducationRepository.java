package com.thoughtworks.gtb.bbasicquiz.repository;

import com.thoughtworks.gtb.bbasicquiz.domain.Education;
import org.springframework.stereotype.Repository;

import java.util.List;

// GTB: + Repository 单独提取了接口，后续可以将接口和实现放到不同的包里
@Repository
public interface EducationRepository {
    Education save(Education education);

    List<Education> findAllByUserId(long userId);
}
