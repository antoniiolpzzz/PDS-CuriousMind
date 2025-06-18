package com.pds.curiousmind.persistence.provider.implementation;

import com.pds.curiousmind.persistence.adapter.implementation.CourseAdapterJPA;
import com.pds.curiousmind.persistence.adapter.implementation.UserAdapterJPA;
import com.pds.curiousmind.persistence.adapter.interfaces.ICourseAdapter;
import com.pds.curiousmind.persistence.adapter.interfaces.IUserAdapter;
import com.pds.curiousmind.persistence.provider.AdapterProvider;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * JPA-based adapter provider for the CuriousMind application.
 * <p>
 * This class is a concrete implementation of {@link AdapterProvider} that supplies singleton
 * instances of JPA-based adapters for courses and users. It initializes a single
 * {@link EntityManagerFactory} for the application's persistence unit and injects it into
 * the adapter singletons ({@link CourseAdapterJPA} and {@link UserAdapterJPA}).
 * </p>
 *
 * <p>
 * The persistence unit name is defined by {@code PERSISTENCE_UNIT_NAME} and must match the
 * configuration in {@code META-INF/persistence.xml}. This provider ensures that all JPA adapters
 * share the same persistence context.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     AdapterProvider provider = new AdapterProviderJPA();
 *     ICourseAdapter courseAdapter = provider.getCourseAdapter();
 *     IUserAdapter userAdapter = provider.getUserAdapter();
 * </pre>
 * </p>
 *
 * @author antoniolopeztoboso
 * @see com.pds.curiousmind.persistence.provider.AdapterProvider
 * @see com.pds.curiousmind.persistence.adapter.implementation.CourseAdapterJPA
 * @see com.pds.curiousmind.persistence.adapter.implementation.UserAdapterJPA
 */
public class AdapterProviderJPA extends AdapterProvider {

    /**
     * The name of the persistence unit as defined in persistence.xml.
     */
    private static final String PERSISTENCE_UNIT_NAME = "curiousmindPU";

    /**
     * The singleton EntityManagerFactory shared by all JPA adapters.
     */
    private final EntityManagerFactory entityManagerFactory;

    /**
     * Constructs the JPA adapter provider and injects the EntityManagerFactory into the adapters.
     * This ensures that all adapters use the same persistence context.
     */
    public AdapterProviderJPA() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        CourseAdapterJPA.INSTANCE.setEntityManagerFactory(entityManagerFactory);
        UserAdapterJPA.INSTANCE.setEntityManagerFactory(entityManagerFactory);
    }

    /**
     * Returns the singleton course adapter instance.
     *
     * @return the JPA-based {@link ICourseAdapter}
     */
    @Override
    public ICourseAdapter getCourseAdapter() {
        return CourseAdapterJPA.INSTANCE;
    }

    /**
     * Returns the singleton user adapter instance.
     *
     * @return the JPA-based {@link IUserAdapter}
     */
    @Override
    public IUserAdapter getUserAdapter() {
        return UserAdapterJPA.INSTANCE;
    }
}
