package com.bht.repositories.impl;

import com.bht.entities.User;
import com.bht.repositories.UserDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;


@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    /*@Autowired
    JdbcTemplate jdbcTemplate;*/

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public int nextIdValue() {
        String sql = "SELECT IDENT_CURRENT('Client') + 1 AS id";
        List results = sessionFactory.getCurrentSession()
                .createSQLQuery(sql).list();

        return ((BigDecimal) results.get(0)).intValue();
    }

    @Override
    public boolean addUser(User user) {
        sessionFactory.getCurrentSession()
                .save(user);

        return true;
    }


    @Override
    public boolean updateUser(User user) {
        sessionFactory.getCurrentSession()
                .merge(user);

        return true;
    }


    @Override
    public boolean deleteUser(int id) {
        sessionFactory.getCurrentSession()
                .delete(getUserById(id));

        return true;
    }


    @Override
    public User getUserById(int id) {
        return sessionFactory.getCurrentSession()
                .get(User.class, id);
    }


    @Override
    public List<User> getAllUsers() {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);

        Root<User> root = query.from(User.class);
        TypedQuery<User> allQuery = sessionFactory.getCurrentSession()
                .createQuery(query.select(root));

        return allQuery.getResultList();
    }
}
