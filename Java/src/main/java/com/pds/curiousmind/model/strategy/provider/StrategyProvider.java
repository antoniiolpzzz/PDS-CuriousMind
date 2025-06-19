package com.pds.curiousmind.model.strategy.provider;

import com.pds.curiousmind.model.strategy.Strategy;
import com.pds.curiousmind.model.strategy.StrategyType;
import com.pds.curiousmind.util.Logger;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Provides dynamic discovery and instantiation of available {@link Strategy} implementations for question ordering and processing.
 * <p>
 * The {@code StrategyProvider} is a singleton enum that scans the {@code com.pds.curiousmind.model.strategy.implementation}
 * package for all classes implementing the {@link Strategy} interface. It supports both enum singletons and regular class
 * implementations. Discovered strategies are mapped by their {@link StrategyType} for fast retrieval.
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li>Uses reflection to discover all {@link Strategy} implementations at runtime.</li>
 *   <li>Handles both enum singletons (with an INSTANCE field) and regular classes.</li>
 *   <li>Maps each strategy to its {@link StrategyType} for easy lookup.</li>
 *   <li>Logs errors using {@link com.pds.curiousmind.util.Logger} if instantiation fails.</li>
 *   <li>Provides a single access point for retrieving strategies by type.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 *     StrategyProvider provider = StrategyProvider.INSTANCE;
 *     Strategy strategy = provider.getStrategy(StrategyType.SEQUENTIAL);
 *     List&lt;Question&gt; questions = strategy.getProcessedQuestions(block);
 * </pre>
 *
 * @author Antonio
 * @since 1.0
 */
public enum StrategyProvider {
    INSTANCE;

    /**
     * The package name where strategy implementations are located.
     * This is used by Reflections to scan for classes.
     * */
    private static final String PACKAGE_NAME = "com.pds.curiousmind.model.strategy.implementation";
    /**
     * Map of strategy types to their corresponding strategy instances.
     * This allows for quick access to strategies based on their type.
     * */
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
                Logger.error("No INSTANCE field found in strategy: " + strategy.getName());
            } catch (InstantiationException e) {
                Logger.error("Cannot instantiate strategy class: " + strategy.getName());
            } catch (ReflectiveOperationException e) {
                Logger.error("Reflective operation failed for strategy: " + strategy.getName());
            } catch (Exception ignored){
                Logger.error("Failed to instantiate strategy: " + strategy.getName());
            }
        }
    }

    /**
     * Retrieves a strategy by its type.
     *
     * @param type the {@link StrategyType} of the strategy to retrieve
     * @return the corresponding {@link Strategy} instance, or null if not found
     */
    public Strategy getStrategy(StrategyType type) {
        return strategies.get(type);
    }
}
