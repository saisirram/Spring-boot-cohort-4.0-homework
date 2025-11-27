package com.sai.cake.baker.service.impl;

import com.sai.cake.baker.service.interfaces.Frosting;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "cake.frosting", havingValue = "chocolate")
public class ChocolateFrosting implements Frosting {
    @Override
    public String getFrosting() {
        return "Chocolate Frosting";
    }
}
