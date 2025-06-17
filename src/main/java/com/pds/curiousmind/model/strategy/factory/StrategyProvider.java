package com.pds.curiousmind.model.strategy.factory;

import com.pds.curiousmind.model.strategy.Strategy;
import com.pds.curiousmind.model.strategy.StrategyType;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum StrategyProvider {
    INSTANCE;

    private static final String PACKAGE_NAME = "com.pds.curiousmind.model.strategy.implementation";
    private final Map<StrategyType, Strategy> strategies = new HashMap<>();

    StrategyProvider() {
        Reflections reflections = new Reflections(PACKAGE_NAME);
        Set<Class<? extends Strategy>> strategyClasses = reflections.getSubTypesOf(Strategy.class);
        for (Class<? extends Strategy> strategy : strategyClasses) {
            try {
                Strategy instance;
                if (strategy.isEnum()) {
                    instance = (Strategy) strategy.getField("INSTANCE").get(null);
                } else {
                    instance = strategy.getDeclaredConstructor().newInstance();
                }
                strategies.put(instance.getStrategyType(), instance);

            }catch (NoSuchFieldException e) {
                System.err.println("No INSTANCE field found in strategy: " + strategy.getName());
            } catch (InstantiationException e) {
                System.err.println("Cannot instantiate strategy class: " + strategy.getName());
            } catch (ReflectiveOperationException e) {
                System.err.println("Reflective operation failed for strategy: " + strategy.getName());
            } catch (Exception ignored){
               System.err.println("Failed to instantiate strategy: " + strategy.getName());
            }
        }
    }

    public Strategy getStrategy(StrategyType type) {
        /* This is the original code that was replaced by the dynamic loading mechanism.
         *
         *         return switch (type) {
         *             case SPACED_REPETITION -> SpacedRepetition.INSTANCE;
         *             case SHUFFLED -> Shuffled.INSTANCE;
         *             case SEQUENTIAL -> Sequential.INSTANCE;
         */
        return strategies.get(type);
    }
}


