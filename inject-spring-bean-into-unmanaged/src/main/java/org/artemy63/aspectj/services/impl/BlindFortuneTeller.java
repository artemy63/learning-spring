package org.artemy63.aspectj.services.impl;

import org.artemy63.aspectj.heroes.Hero;
import org.artemy63.aspectj.services.FortuneTeller;
import org.springframework.stereotype.Service;

@Service
public class BlindFortuneTeller implements FortuneTeller {

    @Override
    public boolean willSurvive(Hero hero) {
        //only guy with rockets can survive
        return hero.mostImportantFeature().contains("rockets");
    }
}
