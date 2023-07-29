package com.jobsity.tenpinbowling.islassolution.handler;

import com.jobsity.tenpinbowling.islassolution.model.Request;
import com.jobsity.tenpinbowling.islassolution.model.Response;

public interface Handler {

    void setNext(Handler handler);

    Response handle(Request request, Response response) throws Exception;

}

