package com.pds.curiousmind.persistence.provider;

import com.pds.curiousmind.persistence.adapter.interfaces.ICourseAdapter;
import com.pds.curiousmind.persistence.adapter.interfaces.IUserAdapter;
import com.pds.curiousmind.util.AppConfig;
import com.pds.curiousmind.util.Logger;

public abstract class AdapterProvider {
    private static AdapterProvider instance;
    private static final String PROVIDER_CLASS_CONFIG_KEY = "persistence.adapter.implementation";

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

    public abstract ICourseAdapter getCourseAdapter();
    public abstract IUserAdapter getUserAdapter();
}