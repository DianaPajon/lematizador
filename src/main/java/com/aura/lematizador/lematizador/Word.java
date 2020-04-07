/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aura.lematizador.lematizador;

import java.util.Objects;

/**
 *
 * @author diana
 */
public class Word {
    private String palabra;
    public Word(String palabra){
        this.palabra = palabra;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Word){
            return Objects.equals(((Word) o).getPalabra().toUpperCase(), this.palabra.toUpperCase());
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        return this.palabra.hashCode();
    }
}
