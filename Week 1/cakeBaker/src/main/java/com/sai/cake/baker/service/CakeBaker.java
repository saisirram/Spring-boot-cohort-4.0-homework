package com.sai.cake.baker.service;

import com.sai.cake.baker.service.interfaces.Frosting;
import com.sai.cake.baker.service.interfaces.Syrup;
import org.springframework.stereotype.Component;

@Component
public class CakeBaker {

    private final Frosting frosting;
    private final Syrup syrup;

    public CakeBaker(Frosting frosting, Syrup syrup) {
        this.frosting = frosting;
        this.syrup = syrup;
    }

    public void bakeCake() {
        System.out.println("Cake is getting baked with " + frosting.getFrosting() + " and " +
                syrup.getSyrup() + ".");
    }
}

