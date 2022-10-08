package com.decipher.olaBackend.models;




public class EchoModel  {
    private String message;
    public EchoModel(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
