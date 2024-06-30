package jpabook.springbootjpa.apiPayload.exception.handler;

import jpabook.springbootjpa.apiPayload.code.BaseErrorCode;
import jpabook.springbootjpa.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
