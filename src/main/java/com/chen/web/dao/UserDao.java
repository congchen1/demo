package com.chen.web.dao;

import com.chen.web.entity.User;

public interface UserDao {
    public int insert(User user);

    public int deleteById(Integer id);

    public int update(User user);

    public User getById(Integer id);

}
