package com.thoughtworks.gtb.bbasicquiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thoughtworks.gtb.bbasicquiz.constants.ExceptionConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "education")
public class Education {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = ExceptionConstants.YEAR_CAN_NO_BE_NULL)
    private Long year;

    @NotNull(message = ExceptionConstants.EDUCATION_TITLE_CAN_NOT_BE_NULL)
    @Length(min = 1,max = 256,message = ExceptionConstants.EDUCATION_TITLE_LENGTH_CONSTRAIN)
    private String title;

    @NotNull(message = ExceptionConstants.EDUCATION_DESCRIPTION_CAN_NOT_BE_NULL)
    @Length(min = 1,max = 4096,message = ExceptionConstants.EDUCATION_DESCRIPTION_LENGTH_CONSTRAIN)
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @Column(name = "userId", updatable=false, insertable=false)
    private Long userId;

}
