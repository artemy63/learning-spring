package org.artemy63.aspectj.services.impl;

import org.artemy63.aspectj.heroes.Hero;
import org.artemy63.aspectj.services.FortuneTeller;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomFortuneTeller implements FortuneTeller {

    @Override
    public boolean willSurvive(Hero hero) {
        return new Random().nextBoolean();
    }
}
