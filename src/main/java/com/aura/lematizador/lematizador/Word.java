/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aura.lematizador.lematizador;

import java.util.Objects;

import opennlp.tools.stemmer.snowball.SnowballStemmer;
import opennlp.tools.stemmer.snowball.SnowballStemmer.ALGORITHM;

/**
 *
 * @author diana
 */
public class Word {
    private String palabra;
    private static SnowballStemmer stemmer = new SnowballStemmer(ALGORITHM.SPANISH); //No será la forma mas elegante de usar un stemmer, pero funciona.
    
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
            return Objects.equals(stemmer.stem(((Word) o).getPalabra().toUpperCase()).toString(), stemmer.stem(this.palabra.toUpperCase()).toString());
        }
        return false;
    }
    
    @Override
    public int hashCode(){
        return this.palabra.toUpperCase().hashCode();
    }
}
