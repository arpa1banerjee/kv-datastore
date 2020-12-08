package com.example.datastore.repository.impl;

import com.example.datastore.repository.DBRepository;
import org.springframework.stereotype.Repository;

@Repository
public class KVDataStoreRepositoryImpl<K, V> implements DBRepository<K, V> {
    private final Entry<K, V>[] table;
    private final int capacity = 10;
    static class Entry<K , V> {
        K key;
        V value;
        Entry<K,V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public KVDataStoreRepositoryImpl() {
        table = new Entry[capacity];
    }

    @Override
    public void put(K key, V data) {
        if(key == null) {
            return;
        }

        int hash = hash(key);

        Entry<K,V> newEntry = new Entry<>(key, data);

        if(table[hash] == null) {
            table[hash] = newEntry;
        } else {
            Entry<K,V> prev = null;
            Entry<K,V> curr = table[hash];

            while(curr != null) {
                if(curr.key.equals(key)) {
                    if(prev == null) {
                        newEntry.next = curr.next;
                        table[hash] = newEntry;
                        return;
                    } else {
                        newEntry.next = curr.next;
                        prev.next = newEntry;
                        return;
                    }
                }
                prev = curr;
                curr = curr.next;
            }
            prev.next = newEntry;
        }
    }

    @Override
    public V get(K key) {
        int hash = hash(key);
        if(table[hash] == null) {
            return null;
        } else {
            Entry<K,V> temp = table[hash];
            while(temp!=null) {
                if(temp.key.equals(key)) {
                    return temp.value;
                }
                temp = temp.next;
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        int hash = hash(key);
        if(table[hash] == null) {
            return false;
        } else{
            Entry<K,V> prev = null;
            Entry<K,V> curr = table[hash];
            while(curr!=null) {
                if(curr.key.equals(key)) {
                    if(prev == null) {
                        table[hash] = table[hash].next;
                        return true;
                    } else {
                        prev.next = curr.next;
                        return true;
                    }
                }
                prev = curr;
                curr = curr.next;
            }
        }
        return false;
    }

    private int hash(K key) {
        return key.hashCode() % capacity;
    }

}
