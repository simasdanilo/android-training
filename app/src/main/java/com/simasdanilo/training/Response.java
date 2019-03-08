package com.simasdanilo.training;

public class Response {
    public boolean ok;
    public Object data;
    public String error;

    private Response (boolean ok, Object data, String error) {
        this.ok = ok;
        this.data = data;
        this.error = error;
    }

    public static Response success(Object data){
        return new Response(true, data, null);
    }

    public static Response failed(String error){
        return new Response(false, null, error);
    }
}
