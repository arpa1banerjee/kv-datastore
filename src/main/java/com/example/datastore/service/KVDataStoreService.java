package com.example.datastore.service;

import com.example.datastore.repository.impl.KVDataStoreRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class KVDataStoreService {

    private final KVDataStoreRepositoryImpl<String, String> repository;

    public KVDataStoreService(KVDataStoreRepositoryImpl<String, String> repository) {
        this.repository = repository;
    }

    public String get(final String key) {
        return Objects.isNull(repository.get(key)) ? "not found" :  repository.get(key);
    }

    public String put(String key, String value) {
        repository.put(key, value);
        return "success";
    }

    public boolean delete(String key) {
        return repository.remove(key);
    }
}
