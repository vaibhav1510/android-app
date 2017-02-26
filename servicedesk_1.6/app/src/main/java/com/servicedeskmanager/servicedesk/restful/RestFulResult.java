package com.servicedeskmanager.servicedesk.restful;

import com.servicedeskmanager.servicedesk.ResponseHandler;

public interface RestFulResult {
    public void onResfulResponse(String result, String responseFor, ResponseHandler handler);
}

