package com.grant.volley;

/**
 * Created by grant on 2018/4/23 0023.
 */

public class BaseReSelt {
    private EntityDis disposeResult;
    private EntityInv invokingResult;

    public BaseReSelt() {}

    public BaseReSelt(EntityDis disposeResult, EntityInv invokingResult) {
        this.disposeResult = disposeResult;
        this.invokingResult = invokingResult;
    }

    public EntityDis getDisposeResult() {
        return disposeResult;
    }

    public void setDisposeResult(EntityDis disposeResult) {
        this.disposeResult = disposeResult;
    }

    public EntityInv getInvokingResult() {
        return invokingResult;
    }

    public void setInvokingResult(EntityInv invokingResult) {
        this.invokingResult = invokingResult;
    }
}
