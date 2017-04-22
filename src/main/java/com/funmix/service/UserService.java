package com.funmix.service;

import java.util.List;
import java.util.Optional;

import com.funmix.entity.User;
import io.vertx.core.Future;

public interface UserService {
	Future<List<User>> getAll();

	Future<Optional<User>> getUser(String userID); 
}
