package com.thoughtworks.gtb.bbasicquiz.constants;

// GTB: + 集中管理各种常量
public class ExceptionConstants {

    public static final String USER_NOT_EXIST_WHEN_GET_EDUCATION = "user no exist with id " ;
    public static final String NOT_FOUND_MESSAGE = "Not Found";
    public static final String REQUEST_PARAM_NOT_VALID_MESSAGE = "request param not valid ";
    public static final String YEAR_CAN_NO_BE_NULL = "year can not be null";
    public static final String EDUCATION_TITLE_CAN_NOT_BE_NULL = "education title can not be null";
    public static final String NAME_CAN_NO_BE_NULL = "name can not be null";
    public static final String NAME_LENGTH_CONSTRAIN = "name length must between 1 and 128";
    public static final String AGE_CAN_NOT_BE_NULL = "age can not be null";
    public static final String AGE_LENGTH_CONSTRAIN = "age must greater than 16";
    public static final String AVATAR_CAN_NOT_BE_NULL = "avatar can not be null";
    public static final String AVATAR_LENGTH_CONSTRAIN = "avatar length must between 8 and 512";
    public static final String USER_DESCRIPTION_LENGTH_CONSTRAIN = "description length must less than 4096";
    public static final String EDUCATION_DESCRIPTION_CAN_NOT_BE_NULL = "education description can not be null";
    public static final String EDUCATION_TITLE_LENGTH_CONSTRAIN = "education title length must between 1 and 256 ";
    public static final String EDUCATION_DESCRIPTION_LENGTH_CONSTRAIN = "education description length must between 1 and 4096 ";
    public static final int NOT_FOUND_STATUS = 404;
    public static final int BAD_REQUEST_STATUS = 400;

    public static final String USER_NOT_EXIST_WHEN_GET_USER_INFO = "Cannot find basic info for user with id " ;
}
