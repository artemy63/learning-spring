package org.artemy63.aspectj.heroes;

import org.springframework.stereotype.Component;

@Component("hero_Superman")
public class Superman implements Hero {

    @Override
    public String mostImportantFeature() {
        return "I can fly";
    }
}
