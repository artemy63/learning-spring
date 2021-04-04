package org.artemy63.aspectj;

import org.artemy63.aspectj.unmanaged.UsualPedestrian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void givenUnmanagedObject_whenInjectSpringManagedBeans_thenSuccessfully() {
        UsualPedestrian kolyan = new UsualPedestrian("Колян");

        Assertions.assertNotNull(kolyan.getIdGenerator());
        Assertions.assertNotNull(kolyan.getIdGenerator().generateId());

        Assertions.assertNotNull(kolyan.getHero());
        Assertions.assertTrue(kolyan.getHero().mostImportantFeature().contains("rockets"));

        Assertions.assertNotNull(kolyan.getBlindFortuneTeller());
        Assertions.assertTrue(kolyan.getBlindFortuneTeller().willSurvive(kolyan.getHero()));

        Assertions.assertNotNull(kolyan.getRanmdomFortuneTeller());

        Assertions.assertDoesNotThrow(kolyan::whatHappened);
    }
}
