package com.revature.scoops.service;

import com.revature.scoops.daos.CustomerDao;
import com.revature.scoops.daos.MenuDao;
import com.revature.scoops.exceptions.ResourcePersistanceException;
import com.revature.scoops.models.Menu;
import com.revature.scoops.util.Logger;

import java.util.List;

public class MenuServices {
    private MenuDao menuDao;
    private Logger logger = Logger.getLogger();
    public MenuServices(MenuDao menuDao) {
        this.menuDao=menuDao;
    }
    public List<Menu> readAll(){
        // TODO: What MenuDao intellisense telling me?
        List<Menu> menus = menuDao.findAll();
        return menus;
    }


    public Menu readById(String id) throws ResourcePersistanceException {

        Menu menu = menuDao.findById(id);
        return menu;
    }

    public Menu update(Menu updatedMenu) {
        if (!menuDao.update(updatedMenu)){
            return null;
        }

        return updatedMenu;
    }

    public boolean delete(String id) {
        return menuDao.delete(id);
    }

}
