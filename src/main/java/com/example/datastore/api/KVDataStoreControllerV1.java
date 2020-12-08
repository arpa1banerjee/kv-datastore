package com.example.datastore.api;

import com.example.datastore.service.KVDataStoreService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/datastore")
public class KVDataStoreControllerV1 {

    private final KVDataStoreService dbService;

    public KVDataStoreControllerV1(KVDataStoreService dbService) {
        this.dbService = dbService;
    }

    @GetMapping("/fetch/{key}")
    public Object get(@PathVariable String key) {
        return dbService.get(key);
    }

    @PostMapping("/insert/{key}/{value}")
    public String get(@PathVariable String key, @PathVariable String value) {
        return dbService.put(key, value);
    }

    @DeleteMapping("/delete/{key}")
    public boolean delete(@PathVariable String key) {
        return dbService.delete(key);
    }
}
