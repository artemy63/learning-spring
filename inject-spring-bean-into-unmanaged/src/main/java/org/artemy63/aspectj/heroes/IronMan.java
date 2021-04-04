package org.artemy63.aspectj.heroes;

import org.springframework.stereotype.Component;

@Component("hero_IronMan")
public class IronMan implements Hero {

    @Override
    public String mostImportantFeature() {
        return "Are you kidding? I also can fly and I have rockets, man!";
    }
}
