package com.pds.curiousmind.persistence.provider.implementation;

import com.pds.curiousmind.persistence.adapter.implementation.CourseAdapterJPA;
import com.pds.curiousmind.persistence.adapter.implementation.UserAdapterJPA;
import com.pds.curiousmind.persistence.adapter.interfaces.ICourseAdapter;
import com.pds.curiousmind.persistence.adapter.interfaces.IUserAdapter;
import com.pds.curiousmind.persistence.provider.AdapterProvider;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class AdapterProviderJPA extends AdapterProvider {

    private static final String PERSISTENCE_UNIT_NAME = "curiousmindPU";

    private final EntityManagerFactory entityManagerFactory;

    protected AdapterProviderJPA() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        CourseAdapterJPA.INSTANCE.setEntityManagerFactory(entityManagerFactory);
        UserAdapterJPA.INSTANCE.setEntityManagerFactory(entityManagerFactory);
    }


    @Override
    public ICourseAdapter getCourseAdapter() {
        return CourseAdapterJPA.INSTANCE;
    }

    @Override
    public IUserAdapter getUserAdapter() {
        return UserAdapterJPA.INSTANCE;
    }
}
