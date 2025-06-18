package com.pds.curiousmind.model.strategy.provider;

import com.pds.curiousmind.model.strategy.Strategy;
import com.pds.curiousmind.model.strategy.StrategyType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StrategyProviderTest {

    @Test
    void testGetSequentialStrategy() {
        Strategy strategy = StrategyProvider.INSTANCE.getStrategy(StrategyType.SEQUENTIAL);
        assertNotNull(strategy);
        assertEquals(StrategyType.SEQUENTIAL, strategy.getStrategyType());
    }

    @Test
    void testGetShuffledStrategy() {
        Strategy strategy = StrategyProvider.INSTANCE.getStrategy(StrategyType.SHUFFLED);
        assertNotNull(strategy);
        assertEquals(StrategyType.SHUFFLED, strategy.getStrategyType());
    }

    @Test
    void testGetSpacedRepetitionStrategy() {
        Strategy strategy = StrategyProvider.INSTANCE.getStrategy(StrategyType.SPACED_REPETITION);
        assertNotNull(strategy);
        assertEquals(StrategyType.SPACED_REPETITION, strategy.getStrategyType());
    }


    @Test
    void testIsSingleton() {
        assertSame(StrategyProvider.INSTANCE, StrategyProvider.INSTANCE);
    }
}
