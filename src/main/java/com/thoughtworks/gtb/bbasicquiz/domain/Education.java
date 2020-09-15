package com.thoughtworks.gtb.bbasicquiz.domain;

import com.thoughtworks.gtb.bbasicquiz.constants.ExceptionConstants;
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

    @NotNull(message = ExceptionConstants.YEAR_CAN_NO_BE_NULL)
    private long year;

    @NotNull(message = ExceptionConstants.EDUCATION_TITLE_CAN_NOT_BE_NULL)
    @Length(min = 1,max = 256,message = ExceptionConstants.EDUCATION_TITLE_LENGTH_CONSTRAIN)
    private String title;

    @NotNull(message = ExceptionConstants.EDUCATION_DESCRIPTION_CAN_NOT_BE_NULL)
    @Length(min = 1,max = 4096,message = ExceptionConstants.EDUCATION_DESCRIPTION_LENGTH_CONSTRAIN)
    private String description;
}
