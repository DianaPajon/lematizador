/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aura.lematizador.lematizador;

import java.util.Collection;
import java.util.Set;

/**
 *
 * @author diana
 */
public class SynSet {
    private String lema;
    private Collection<Word> instancias;
    
    public SynSet(String lema){
        this.lema = lema;
    }
    
    private static Collection<Relationship> relaciones;

    public String getLema() {
        return lema;
    }

    public void setLema(String lema) {
        this.lema = lema;
    }

    public Collection<Word> getInstancias() {
        return instancias;
    }

    public void setInstancias(Collection<Word> instancias) {
        this.instancias = instancias;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof SynSet){
            return ((SynSet) o).getLema().toUpperCase() == this.getLema().toUpperCase();
        }
        else
            return false;
    }
    
    @Override
    public int hashCode(){
        return lema.hashCode();
    }
}
