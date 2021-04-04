package org.artemy63.aspectj.services.impl;

import org.artemy63.aspectj.services.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("uuid_IdGenerator")
public class UUIDIdGeneratorImpl implements IdGenerator {

    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
