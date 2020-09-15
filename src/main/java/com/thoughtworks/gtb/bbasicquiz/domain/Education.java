package com.thoughtworks.gtb.bbasicquiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Education {

    private long userId;

    @NotNull(message = "历年不可为空")
    private long year;

    @NotNull(message = "教育经历标题不可为空")
    @Length(min = 1,max = 256,message = "教育经历标题必须大于1并小于256")
    private String title;

    @NotNull(message = "教育经历描述不可为空")
    @Length(min = 1,max = 256,message = "教育经历描述必须大于1并小于4096")
    private String description;
}
