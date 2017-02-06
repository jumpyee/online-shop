package com.sombra.stoliar.model;

public class FormResponse {

    private boolean status;

    private Object result;

    public FormResponse() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}