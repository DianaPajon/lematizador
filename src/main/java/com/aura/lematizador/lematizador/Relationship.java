/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aura.lematizador.lematizador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author diana
 */

//Relaciones de orden entre elementos de un mismo tipo.
//TODO: buscar la forma de representar eficientemente un látice.
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
        Set<A> imagen = new HashSet<>();
        for(Pair<A,A> par : this.elementos){
            if(par.getSecond().equals(elemento)){
                imagen.add(par.getFirst());
            }
        }
        return imagen;
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
    
    public static <X> void addElement(Set<Relationship <X>> relaciones, String nombre, Pair<X,X> elemento){
        for(Relationship<X> rel: relaciones){
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
    
    public int profundidad(A elemento) {
    	Set<A> preImagen = this.getPreimagen(elemento);
    	if(preImagen.equals(Collections.emptySet())) {
    		return 0;
    	}
    	int profundidad = 0;
    	Set<Set<A>> siguienteNivel = new HashSet<Set<A>>();
    	siguienteNivel.add(preImagen);
    	while(siguienteNivel.stream().allMatch(s->!s.equals(Collections.emptySet()))){
    		Set<Set<A>> nuevoNivel = new HashSet<Set<A>>();
			for(Set<A> x:siguienteNivel ) {
				for(A a:x) {
					nuevoNivel.add(this.getPreimagen(a));
				}
			}
			siguienteNivel = nuevoNivel;
			profundidad++;
    	}
    	return profundidad;
    }
    
    
    public A getMayorAncestroComun(A elemento1, A elemento2) {
    	List<A> ancestros1 = new ArrayList<A>(Arrays.asList(elemento1));
    	List<A> ancestros2 =new ArrayList<A>(Arrays.asList(elemento2));
    	
    	Set<A> siguienteNivel = this.getPreimagen(elemento1);
    	while(!siguienteNivel.equals(Collections.emptySet())) {
    		ancestros1.addAll(siguienteNivel);
    		Set<A> nuevoNivel = new HashSet<>();
    		for(A a : siguienteNivel) {
    			nuevoNivel.addAll(this.getPreimagen(a));
    		}
    		siguienteNivel = nuevoNivel;
    	}
    	
    	siguienteNivel = this.getPreimagen(elemento2);
    	while(!siguienteNivel.equals(Collections.emptySet())) {
    		ancestros2.addAll(siguienteNivel);
    		Set<A> nuevoNivel = new HashSet<>();
    		for(A a : siguienteNivel) {
    			nuevoNivel.addAll(this.getPreimagen(a));
    		}    		
    		siguienteNivel = nuevoNivel;

    	}
    	
    	if(ancestros1.size() == 1) {
    		if(ancestros2.size() == 1) {
    			if(elemento1.equals(elemento2)) {
    				return elemento1;
    			}else {
    				//Esto debería pasar?
    				return null;
    			}
    		}
    		if(ancestros2.contains(elemento1)) {
    			return elemento1;
    		} else {
				//Esto debería pasar?
				return null;
    			
    		}
    	}
    	
    	if(ancestros2.size() == 1) {
    		if(ancestros1.size() == 1) {
    			if(elemento1.equals(elemento2)) {
    				return elemento1;
    			}else {
    				//Esto debería pasar?
    				return null;
    			}
    		}
    		if(ancestros1.contains(elemento1)) {
    			return elemento2;
    		} else {
    			//Esto debería pasar?
    			return null;
    			
    		}
    		
    	}
    	
    	/*
    	 * El primer elemento común, es el primer elemento de ancestros1 que está también en ancestros2?
    	 * Supongamos que fuera otro, X, ese estaría ANTES den ancestros1 y en ancestros 2. Por lo tanto, es un absurdo.
    	 * Es el primero que vemos.
    	 * 
    	 * (Ponele)
    	 * 
    	 * (Cuadrático no es taaaaaan malo, ¿cuanta profunidad pueden tener estos árboles?)
    	 */
    	for(A elemento : ancestros1) {
    		if(ancestros2.contains(elemento)) {
    			return elemento;
    		}
    	}
    	
    	return null;
    }
    
}
