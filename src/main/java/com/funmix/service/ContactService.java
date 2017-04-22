package com.funmix.service;

import java.util.List;
import java.util.Optional;

import com.funmix.entity.Contact;

import io.vertx.core.Future;

public interface ContactService {

	Future<Optional<Contact>> getContact(String contactID);

	Future<List<Contact>> getAll();

	Future<Boolean> insert(Contact contact);

}
