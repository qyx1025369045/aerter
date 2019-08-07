package com.example.daye5.http;

public class ApiException extends Throwable {

    private int errorCode;

    private String verror;

    public ApiException(int errorCode, String verror) {
        this.errorCode = errorCode;
        this.verror = verror;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getVerror() {
        return verror;
    }

    public void setVerror(String verror) {
        this.verror = verror;
    }
}
