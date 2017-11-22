package org.artemy63.spring.ripper.quoters;


import org.artemy63.spring.ripper.annotations.InjectRandomInt;
import org.artemy63.spring.ripper.annotations.PostProxy;
import org.artemy63.spring.ripper.annotations.Profiling;

import javax.annotation.PostConstruct;

@Profiling
public class TerminatorQuoter implements Quoter {

    @InjectRandomInt(min = 2, max = 7)
    private int repeat;

    private String message;

    public TerminatorQuoter() {
        System.out.println("Phase 1 :: constructor");
        System.out.println(repeat); //not initialized yet
    }

    @PostConstruct
    public void initMethod() {
        System.out.println("Phase 2 :: init method");
        System.out.println(repeat);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
//    @PostConstruct
    //профилирование не работает в этом случае с PostConstruct, так как никаких прокси еще не создано
    @PostProxy
    public void sayQuote() {
        System.out.println("Phase 3 :: context refreshed");
        for (int i = 0; i < repeat; i++) {
            System.out.println(message);
        }
    }
}
