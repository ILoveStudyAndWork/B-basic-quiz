package com.thoughtworks.gtb.bbasicquiz.domain;

import com.thoughtworks.gtb.bbasicquiz.constants.ExceptionConstants;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = ExceptionConstants.NAME_CAN_NO_BE_NULL)
    @Length(min = 1,max = 128,message = ExceptionConstants.NAME_LENGTH_CONSTRAIN)
    private String name;

    @NotNull(message = ExceptionConstants.AGE_CAN_NOT_BE_NULL)
    @Min(value = 16,message = ExceptionConstants.AGE_LENGTH_CONSTRAIN)
    private long age;

    @NotNull(message = ExceptionConstants.AVATAR_CAN_NOT_BE_NULL)
    @Length(min = 8, max = 512, message = ExceptionConstants.AVATAR_LENGTH_CONSTRAIN)
    private String avatar;

    @Length(max = 1024, message = ExceptionConstants.USER_DESCRIPTION_LENGTH_CONSTRAIN)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List<Education> educations;
}

