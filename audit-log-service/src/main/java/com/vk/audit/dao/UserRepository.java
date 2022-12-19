package com.vk.audit.dao;

import org.springframework.data.repository.CrudRepository;

import com.vk.audit.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByNameIgnoreCase(String name);
}