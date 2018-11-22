package ua.lviv.iot.persistence.dao;

import ua.lviv.iot.Clothes;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import java.io.Serializable;

public class ClothesDaoImplementation extends  AbstractDao<Clothes> implements ClothesDao, Serializable {
    private EntityManager entityManager;

    @Override
    protected Class<Clothes> getEntityClass() {
        return Clothes.class;
    }

    @Resource
    private UserTransaction userTransaction;
}
