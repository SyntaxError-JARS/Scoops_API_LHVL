package com.revature.scoops.daos;

import com.revature.scoops.models.Menu;
import com.revature.scoops.util.Crudable;

import java.io.IOException;
import java.util.List;

public class MenuDao implements Crudable<Menu> {
    @Override
    public Menu create(Menu newObject) {
        return null;
    }

    @Override
    public List<Menu> findAll() throws IOException {
        return null;
    }

    @Override
    public Menu findById(String id) {
        return null;
    }

    @Override
    public boolean update(Menu updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
