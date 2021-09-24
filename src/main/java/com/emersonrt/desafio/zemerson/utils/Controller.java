package com.emersonrt.desafio.zemerson.utils;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;

/**
 *
 * @author emerson
 */

public abstract class Controller {
    
    private static final String MESSAGE_TYPE = "message";
    private static final String DATA_TYPE = "data";
    private static final String STATUS_TYPE = "status";
    
    private final Map<String, Object> build = new HashMap<>();

    public Controller() {
    }

    protected void addData(Object obj) {
        
        if (obj == null) {
            addMessage("No data found!");
            return;
        }
        
        build.put(DATA_TYPE, obj);
        build.put(MESSAGE_TYPE, "Success!");
        build.put(STATUS_TYPE, 200);
    }
    
    protected void addMessage(String message) {
        build.put(DATA_TYPE, "message");
        build.put(MESSAGE_TYPE, message);
        build.put(STATUS_TYPE, 200);
    }
    
    protected void addError(String message) {
        build.put(DATA_TYPE, "error");
        build.put(MESSAGE_TYPE, message);
        build.put(STATUS_TYPE, 404);
    }
    
    protected Response build() {
        return Response.ok(build).build();
    }

}
