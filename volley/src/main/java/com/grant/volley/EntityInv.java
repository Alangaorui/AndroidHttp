package com.grant.volley;

/**
 * Created by grant on 2018/4/23 0023.
 */

public class EntityInv {
    private String alertCode;
    private String invoking;
    private String message;

    public EntityInv() {}

    public EntityInv(String alertCode, String invoking, String message) {
        this.alertCode = alertCode;
        this.invoking = invoking;
        this.message = message;
    }

    public String getAlertCode() {
        return alertCode;
    }

    public void setAlertCode(String alertCode) {
        this.alertCode = alertCode;
    }

    public String getInvoking() {
        return invoking;
    }

    public void setInvoking(String invoking) {
        this.invoking = invoking;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
