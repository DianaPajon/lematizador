/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aura.lematizador.lematizador;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author diana
 */
public class Lematizador {
    private Set<SynSet> lemas;
    private Set<Relationship<SynSet, SynSet>> relacionesSemanticas;
    private Set<Relationship<Word, Word>> relacionesLexicograficas;
    
    private Lematizador(String archivo){
        lemas = new HashSet<>();
        relacionesSemanticas = new HashSet<>();
        relacionesLexicograficas = new HashSet<>();
    }

    public Set<SynSet> getLemas() {
        return lemas;
    }

    public void setLemas(Set<SynSet> lemas) {
        this.lemas = lemas;
    }

    public Set<Relationship<SynSet, SynSet>> getRelacionesSemanticas() {
        return relacionesSemanticas;
    }

    public void setRelacionesSemanticas(Set<Relationship<SynSet, SynSet>> relacionesSemanticas) {
        this.relacionesSemanticas = relacionesSemanticas;
    }

    public Set<Relationship<Word, Word>> getRelacionesLexicograficas() {
        return relacionesLexicograficas;
    }

    public void setRelacionesLexicograficas(Set<Relationship<Word, Word>> relacionesLexicograficas) {
        this.relacionesLexicograficas = relacionesLexicograficas;
    }

    
}
