package com.servicedeskmanager.servicedesk;

/**
 * Created by cb-vaibhav on 26/02/17.
 */

public class ResponseHandler {

    private Boolean isSuccess;
    public Exception exp;

    private ResponseHandler(Boolean isSuccess, Exception exp){
        this.isSuccess=isSuccess;
        this.exp=exp;
    }

    public static ResponseHandler createSuccessful() {
        return new ResponseHandler(true, null);
    }


    public static ResponseHandler createForFail(Exception exp){
        return new ResponseHandler(false, exp);
    }

    public boolean successful(){
        return isSuccess;
    }


}
