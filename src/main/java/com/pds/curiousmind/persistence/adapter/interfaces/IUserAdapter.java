package com.pds.curiousmind.persistence.adapter.interfaces;

import com.pds.curiousmind.model.question.option.Option;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.persistence.adapter.IAdapter;

import java.util.Optional;

public interface IUserAdapter extends IAdapter<User> {

    Optional<User> findByUsername(String username);
}
