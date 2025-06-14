package com.pds.curiousmind.persistence.adapter.interfaces;

import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.persistence.adapter.IAdapter;

import java.util.List;

public interface IUserAdapter extends IAdapter<User> {

    User findByUsername(String username);
}

