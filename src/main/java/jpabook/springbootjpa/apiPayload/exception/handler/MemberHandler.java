package jpabook.springbootjpa.apiPayload.exception.handler;

import jpabook.springbootjpa.apiPayload.code.BaseErrorCode;
import jpabook.springbootjpa.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode);}

}
