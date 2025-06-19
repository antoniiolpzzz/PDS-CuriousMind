package com.pds.curiousmind.persistence.provider;

import com.pds.curiousmind.persistence.adapter.interfaces.ICourseAdapter;
import com.pds.curiousmind.persistence.adapter.interfaces.IUserAdapter;
import com.pds.curiousmind.util.AppConfig;
import com.pds.curiousmind.util.Logger;

/**
 * Abstract provider for persistence adapters in the CuriousMind application.
 * <p>
 * This class is responsible for supplying singleton instances of adapter implementations
 * for different domain entities (such as courses and users). The concrete implementation
 * is determined at runtime based on the configuration property
 * {@code persistence.adapter.implementation} in {@code config.properties}.
 * </p>
 *
 * <p>
 * The provider uses reflection to instantiate the specified implementation class, ensuring
 * that only one instance exists (singleton pattern). If the configuration is missing or
 * instantiation fails, appropriate errors are logged and exceptions are thrown.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     AdapterProvider provider = AdapterProvider.INSTANCE();
 *     ICourseAdapter courseAdapter = provider.getCourseAdapter();
 *     IUserAdapter userAdapter = provider.getUserAdapter();
 * </pre>
 * </p>
 *
 * @author antoniolopeztoboso
 * @see com.pds.curiousmind.persistence.adapter.interfaces.ICourseAdapter
 * @see com.pds.curiousmind.persistence.adapter.interfaces.IUserAdapter
 */
public abstract class AdapterProvider {
    /**
     * Singleton instance of the adapter provider.
     */
    private static AdapterProvider instance;

    /**
     * Configuration key for the provider implementation class name.
     */
    private static final String PROVIDER_CLASS_CONFIG_KEY = "persistence.adapter.implementation";

    /**
     * Returns the singleton instance of the adapter provider, instantiating it if necessary.
     * <p>
     * The implementation class is determined by the configuration property
     * {@code persistence.adapter.implementation}. If the property is not set or instantiation fails,
     * an error is logged and a runtime exception is thrown.
     * </p>
     *
     * @return the singleton {@link AdapterProvider} instance
     * @throws RuntimeException if instantiation fails or configuration is missing
     */
    public static AdapterProvider INSTANCE() {
        if (instance == null) {
            String providerClass = AppConfig.get(PROVIDER_CLASS_CONFIG_KEY);
            if (providerClass != null && !providerClass.isEmpty()) {
                try {
                    instance = (AdapterProvider) Class.forName(providerClass).getDeclaredConstructor().newInstance();
                    return instance;
                } catch (Exception e) {
                    Logger.error("Failed to instantiate AdapterProvider: " + providerClass + " | " + e.getMessage());
                    throw new RuntimeException("Failed to instantiate AdapterProvider: " + providerClass, e);
                }
            }
            Logger.error("No AdapterProvider implementation specified in config.properties. Please set 'adapter.provider.class'.");
            throw new IllegalStateException("No AdapterProvider implementation specified. Please set 'adapter.provider.class' in config.properties.");
        }
        return instance;
    }

    /**
     * Returns the course adapter instance.
     *
     * @return the {@link ICourseAdapter} implementation
     */
    public abstract ICourseAdapter getCourseAdapter();

    /**
     * Returns the user adapter instance.
     *
     * @return the {@link IUserAdapter} implementation
     */
    public abstract IUserAdapter getUserAdapter();
}