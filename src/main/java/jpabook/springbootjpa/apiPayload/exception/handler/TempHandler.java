package jpabook.springbootjpa.apiPayload.exception.handler;

import jpabook.springbootjpa.apiPayload.code.BaseErrorCode;
import jpabook.springbootjpa.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}