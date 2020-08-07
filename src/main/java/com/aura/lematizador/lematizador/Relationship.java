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

//Relaciones entre elementos de un mismo tipo.
public class Relationship<A> {

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
    
    private Set<Pair<A,A>> elementos;
    
    public Relationship(String nombreDirecto, String nombreInverso){
        this.nombreDirecto = nombreDirecto;
        this.nombreInversa = nombreInverso;
        this.elementos = new HashSet<>();
    }
    
    public void addElement(Pair<A,A> elemento){
        this.elementos.add(elemento);
    }
    
    public boolean checkElement(Pair<A,A> elemento){
        return this.elementos.contains(elemento);
    }
    
    public Set<Pair<A,A>> getElementos(){
        return elementos;
    }
    
    public Set<A> getPreimagen(A elemento){
        Set<A> preimagen = new HashSet<>();
        for(Pair<A,A> par : this.elementos){
            if(par.getSecond().equals(elemento)){
                preimagen.add(par.getFirst());
            }
        }
        return preimagen;
    }
    
    public Set<A> getImagen(A elemento){
        Set<A> imagen = new HashSet<>();
        for(Pair<A,A> par : this.elementos){
            if(par.getFirst().equals(elemento)){
                imagen.add(par.getSecond());
            }
        }
        return imagen;
    }
    
    public static <X,Y> void addElement(Set<Relationship <X>> relaciones, String nombre, Pair<X,X> elemento){
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
    
    public int profunidad(A elemento) {
    	return 0;
    }
    
    
    public A getAncestroComun(A elemento1, A elemento2) {
    	return null;
    }
    
}
