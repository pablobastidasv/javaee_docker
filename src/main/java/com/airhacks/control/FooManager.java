/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.control;

import javax.ejb.Stateless;

/**
 *
 * @author pablobastidasv
 */
@Stateless
public class FooManager {
    
    public String saludar(String name){
        return String.format("Hola %s", name);
    }
    
}
