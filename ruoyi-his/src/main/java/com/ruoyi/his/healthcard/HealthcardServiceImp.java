package com.ruoyi.his.healthcard;

import com.tencent.healthcard.impl.HealthCardServerImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class HealthcardServiceImp implements HealthcardService {

    @Autowired
    private HealthCardServerImpl healthCardServer;
}
