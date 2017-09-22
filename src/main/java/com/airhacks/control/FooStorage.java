/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.control;

import com.airhacks.entity.Item;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

/**
 *
 * @author pablobastidasv
 */
@Singleton
public class FooStorage {
    
    private ConcurrentHashMap<Long, Item> storage;
    private AtomicLong ids;
    
    @PostConstruct
    public void init(){
        this.storage = new ConcurrentHashMap<>();
    }
    
    public Long save(String name){
        long id = ids.getAndIncrement();
        storage.put(id, new Item(id, name));
        return id;
    }
    
    @Lock(LockType.READ)
    public Item get(Long id){
        return storage.get(id);
    }
    
    public List<Item> list(){
        return storage.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
