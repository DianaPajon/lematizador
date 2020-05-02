/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aura.lematizador.lematizador;

import java.util.Objects;
import javax.print.attribute.HashAttributeSet;

/**
 *
 * @author diana
 */
public class Pair<T,U> {
    private T first;
    private U second;
    public Pair(T first, U second){
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public U getSecond() {
        return second;
    }

    public void setSecond(U second) {
        this.second = second;
    }
    
    public Pair<U,T> invertir(){
        return new Pair(this.getSecond(), this.getFirst());
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(first, second);
    }
    
    @Override
    public boolean equals(Object otro){
        if (otro instanceof Pair){
            return Objects.equals(((Pair) otro).getFirst(), this.getFirst()) && 
                   Objects.equals(((Pair) otro).getSecond(), this.getSecond());
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "(" + this.first.toString() + " , " + this.second.toString() + ")";
    }
}
