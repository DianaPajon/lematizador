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
public class Relationship<A,B> {

    public String getNombreDirecto() {
        return nombreDirecto;
    }

    public void setNombreDirecto(String nombreDirecto) {
        this.nombreDirecto = nombreDirecto;
    }

    public String getNombreInversa() {
        return nombreInversa;
    }

    public void setNombreInversa(String nombreInversa) {
        this.nombreInversa = nombreInversa;
    }
    private String nombreDirecto;
    private String nombreInversa;
    
    private Set<Pair<A,B>> elementos;
    
    public Relationship(String nombreDirecto, String nombreInverso){
        this.nombreDirecto = nombreDirecto;
        this.nombreInversa = nombreInverso;
        this.elementos = new HashSet<>();
    }
    
    public void addElement(Pair<A,B> elemento){
        this.elementos.add(elemento);
    }
    
    public boolean checkElement(Pair<A,B> elemento){
        return this.elementos.contains(elemento);
    }
    
    public Set<Pair<A,B>> getElementos(){
        return elementos;
    }
    
    public Set<A> getPreimagen(B elemento){
        Set<A> preimagen = new HashSet<>();
        for(Pair<A,B> par : this.elementos){
            if(par.getSecond().equals(elemento)){
                preimagen.add(par.getFirst());
            }
        }
        return preimagen;
    }
    
    public Set<B> getImagen(A elemento){
        Set<B> imagen = new HashSet<>();
        for(Pair<A,B> par : this.elementos){
            if(par.getFirst().equals(elemento)){
                imagen.add(par.getSecond());
            }
        }
        return imagen;
    }
    
    public static <X,Y> void addElement(Set<Relationship <X,Y>> relaciones, String nombre, Pair<X,Y> elemento){
        for(Relationship rel: relaciones){
            int tipo = 0;
            if(rel.getNombreDirecto().equals(nombre)){
                tipo = 1;
            } else if(rel.getNombreInversa().equals(nombre)) {
                tipo = 2;
            }
            if(tipo == 1){
                rel.addElement(elemento);
                break;
            }
            else if(tipo == 2){
                rel.addElement(elemento.invertir());
                break;
            }
        }
    }
}
