package org.artemy63.aspectj.unmanaged;

import org.artemy63.aspectj.heroes.Hero;
import org.artemy63.aspectj.services.FortuneTeller;
import org.artemy63.aspectj.services.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;

@Configurable
public class UsualPedestrian {

    private String name;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    @Qualifier("hero_IronMan")
    private Hero hero;

    @Autowired
    @Qualifier("blindFortuneTeller")
    private FortuneTeller blindFortuneTeller;

    @Autowired
    @Qualifier("randomFortuneTeller")
    private FortuneTeller ranmdomFortuneTeller;

    public UsualPedestrian(String name) {
        this.name = name;
    }

    public void whatHappened() {
        System.out.println("record number " + idGenerator.generateId() + " for pedestrian " + name);
        System.out.println("blind fortune teller says " + getBlindFortuneTeller().willSurvive(hero));
        System.out.println("random fortune teller says " + getRanmdomFortuneTeller().willSurvive(hero));
    }

    public IdGenerator getIdGenerator() {
        return idGenerator;
    }

    public Hero getHero() {
        return hero;
    }

    public FortuneTeller getBlindFortuneTeller() {
        return blindFortuneTeller;
    }

    public FortuneTeller getRanmdomFortuneTeller() {
        return ranmdomFortuneTeller;
    }
}
