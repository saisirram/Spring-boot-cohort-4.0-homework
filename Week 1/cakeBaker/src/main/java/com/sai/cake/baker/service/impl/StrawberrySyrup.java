package com.sai.cake.baker.service.impl;

import com.sai.cake.baker.service.interfaces.Syrup;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "cake.syrup", havingValue = "strawberry")
public class StrawberrySyrup implements Syrup {

    @Override
    public String getSyrup() {
        return "Strawberry Syrup";
    }
}
