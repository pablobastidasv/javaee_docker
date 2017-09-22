/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.entity;

import javax.json.Json;
import javax.json.JsonObject;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author pablobastidasv
 */
@XmlAccessorType(FIELD)
public class Item {
    private Long id;
    private String name;

    public Item() {
    }

    public Item(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public JsonObject getJson(){
        return Json.createObjectBuilder()
                .add("id", id)
                .add("name", name)
                .build();
    }
}
