package com.thoughtworks.gtb.bbasicquiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    @Length(min = 1,max = 128,message = "名字长度必须大于1并小于128")
    private String name;
    @Min(value = 16,message = "年龄必须大于16")
    private long age;
    @Length(min = 8, max = 512, message = "头像图片链接地址长度必须大于8并小于512")
    private String avatar;
    @Length(max = 1024, message = "个人介绍信息长度必须小于1024")
    private String description;
}
