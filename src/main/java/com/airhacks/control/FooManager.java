/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.control;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author pablobastidasv
 */
@Stateless
public class FooManager {
    @Inject
    FooStorage fooStorage;
    
    public String saludar(String name){
        fooStorage.save(name);
        return String.format("Hola %s", name);
    }
}
