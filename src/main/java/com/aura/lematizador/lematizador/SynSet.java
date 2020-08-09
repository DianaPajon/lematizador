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
    private String clase;
    
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

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clase == null) ? 0 : clase.hashCode());
		result = prime * result + ((lema == null) ? 0 : lema.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SynSet other = (SynSet) obj;
		if (clase == null) {
			if (other.clase != null)
				return false;
		} else if (!clase.equals(other.clase))
			return false;
		if (lema == null) {
			if (other.lema != null)
				return false;
		} else if (!lema.equals(other.lema))
			return false;
		return true;
	}
    
    
    
    
}
