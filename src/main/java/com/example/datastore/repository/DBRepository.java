package com.example.datastore.repository;

public interface DBRepository<K, V> {

    void put(K key, V val);

    V get(K key);

    boolean remove(K key);

}
