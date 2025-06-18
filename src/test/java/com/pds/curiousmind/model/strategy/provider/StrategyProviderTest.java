package com.pds.curiousmind.model.strategy.provider;

import com.pds.curiousmind.model.strategy.Strategy;
import com.pds.curiousmind.model.strategy.StrategyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StrategyProviderTest {

    private StrategyProvider provider;

    @BeforeEach
    void setUp() {
        provider = StrategyProvider.INSTANCE;
    }

    @Test
    void testLoadSequentialStrategy() {
        Strategy strategy = provider.getStrategy(StrategyType.SEQUENTIAL);
        assertNotNull(strategy, "Sequential strategy should not be null");
        assertEquals(StrategyType.SEQUENTIAL, strategy.getStrategyType());
    }

    @Test
    void testLoadShuffledStrategy() {
        Strategy strategy = provider.getStrategy(StrategyType.SHUFFLED);
        assertNotNull(strategy, "Shuffled strategy should not be null");
        assertEquals(StrategyType.SHUFFLED, strategy.getStrategyType());
    }

    @Test
    void testLoadSpacedRepetitionStrategy() {
        Strategy strategy = provider.getStrategy(StrategyType.SPACED_REPETITION);
        assertNotNull(strategy, "SpacedRepetition strategy should not be null");
        assertEquals(StrategyType.SPACED_REPETITION, strategy.getStrategyType());
    }

    @Test
    void testGetStrategyWithNullTypeReturnsNull() {
        Strategy strategy = provider.getStrategy(null);
        assertNull(strategy, "getStrategy(null) should return null");
    }
}
