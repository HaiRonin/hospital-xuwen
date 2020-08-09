package com.ruoyi.his.remote;

public class Test {
    public static void main(String[] args) {
        AbstractHisServiceHandler handler = new DoregInfoHisServiceHander();
        handler.invokeCall(1l);
    }
}
