package ru.milandr.courses.myshop.services;

import org.springframework.stereotype.Service;
import ru.milandr.courses.myshop.daos.GoodDao;
import ru.milandr.courses.myshop.entities.Good;

@Service
public class GoodService {

    private GoodDao goodDao;

    public GoodService(GoodDao goodDao) {
        this.goodDao = goodDao;
    }

    public void demoMethod() {
        Good good = new Good();

        goodDao.save(good);
    }
}