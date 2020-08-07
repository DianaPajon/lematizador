/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aura.lematizador.lematizador;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author diana
 */
public class Lematizador {
    private Set<SynSet> lemas;
    private Set<Word> palabras;
    
    //Por qué no usé hashmap?, explicame?
    //TODO: Rehacer con hashmap.
    private Set<Relationship<SynSet>> relacionesSemanticas;
    private Set<Relationship<Word>> relacionesLexicograficas;
    
    //Constructor por DOM.
    public Lematizador(Document documento){
        lemas = new HashSet<>();
        relacionesSemanticas = new HashSet<>();
        relacionesLexicograficas = new HashSet<>();
        Set<Relationship<SynSet>> relacionesSemanticas = new HashSet();
        Set<Relationship<Word>> relacionesLexicograficas = new HashSet();
        
        NodeList relaciones = documento.getElementsByTagName("relacion");
        for(int i = 0; i < relaciones.getLength(); i++){
            Node relacion = relaciones.item(i);
            NamedNodeMap atributos = relacion.getAttributes();
            Node tipo = atributos.getNamedItem("tipo");
            Node directa = atributos.getNamedItem("directa");
            Node inversa = atributos.getNamedItem("inversa");
            
            switch(tipo.getNodeValue()){
                case "sem":
                    Relationship<SynSet> synrel = new Relationship(directa.getNodeValue(), inversa!=null?inversa.getNodeValue():directa.getNodeValue());
                    relacionesSemanticas.add(synrel);
                    break;
                case "lex":
                    Relationship<Word> lexrel = new Relationship(directa.getNodeValue(), inversa!=null?inversa.getNodeValue():directa.getNodeValue());
                    relacionesLexicograficas.add(lexrel);
                    break;
                default:
                    break;
            }

        }
        //Inicializo todos los SynSets
        NodeList synsetlist = documento.getElementsByTagName("synset");
        Map<String, SynSet> synsets = new HashMap<>();
        Map<String, Word> words = new HashMap();
        Set<Pair<String, Pair<String,String>>> sems = new HashSet();
        Set<Pair<String, Pair<String, String>>> lexs = new HashSet();
        for(int i = 0; i < synsetlist.getLength();i++){
            Node synsetNode = synsetlist.item(i);
            NamedNodeMap atributos = synsetNode.getAttributes();
            String nombreLema = atributos.getNamedItem("lema").getNodeValue();
            String familiaLema = atributos.getNamedItem("type").getNodeValue();
            Set<Word> synSetWords = new HashSet();
            for(int j = 0; j < synsetNode.getChildNodes().getLength(); j++){
                Node nodo = synsetNode.getChildNodes().item(j);
                switch (nodo.getNodeName()){
                    case "sem":
                        String nombreRelacionSemantica = nodo.getAttributes().getNamedItem("relacion").getNodeValue();
                        String objetivoRelacion = nodo.getAttributes().getNamedItem("lema").getNodeValue();
                        sems.add(new Pair(nombreRelacionSemantica, new Pair(nombreLema, objetivoRelacion)));
                        break;
                    case "word":
                        String nombrePalabra = nodo.getAttributes().getNamedItem("lex").getNodeValue();
                        for(int k = 0; k <  nodo.getChildNodes().getLength();k++){
                            Node relnode = nodo.getChildNodes().item(k);
                            if(relnode.getNodeName() != "lex")
                                continue;
                            String nombreRelacionLex = relnode.getAttributes().getNamedItem("relacion").getNodeValue();
                            String objetivoRelacionLex = relnode.getAttributes().getNamedItem("word").getNodeValue();
                            lexs.add(new Pair(nombreRelacionLex, new Pair(nombrePalabra,objetivoRelacionLex)));
                            
                        }
                        Word palabra = new Word(nombrePalabra);
                        synSetWords.add(palabra);
                        words.put(nombrePalabra, palabra);
                        break;
                    default:
                        break;
                }
            }
            SynSet synSet = new SynSet(nombreLema);
            synSet.setClase(familiaLema);
            synSet.setInstancias(synSetWords);
            synsets.put(nombreLema, synSet);
        }
        
        for(Pair<String, Pair<String, String>> tripla : sems){
            String nombreRelacion =  tripla.getFirst();
            SynSet origen = synsets.get(tripla.getSecond().getFirst());
            SynSet objetivo = synsets.get(tripla.getSecond().getSecond());
            
            if(origen == null || objetivo == null)
                continue;
            
            Relationship.addElement(relacionesSemanticas, nombreRelacion, new Pair(origen, objetivo));
        }
        
        for(Pair<String, Pair<String, String>> tripla : lexs){
            String nombreRelacion =  tripla.getFirst();
            Word origen = words.get(tripla.getSecond().getFirst());
            Word objetivo = words.get(tripla.getSecond().getSecond());
            
            if(origen == null || objetivo == null)
                continue;
            
            Relationship.addElement(relacionesLexicograficas, nombreRelacion, new Pair(origen, objetivo));
        }
        //Ahora, teniendo los todo, genero el lematizador.
        
        this.lemas = new HashSet(synsets.values());
        this.palabras = new HashSet(words.values());
        this.relacionesLexicograficas = relacionesLexicograficas;
        this.relacionesSemanticas = relacionesSemanticas;
    }

    public Set<SynSet> encontrarLema(Word w){
        Set<SynSet> lemas = new HashSet<>();
        for(SynSet s : this.lemas){
            if(s.getInstancias().contains(w)){
                lemas.add(s);
            }
        }
        return lemas;
    }

    public void setRelacionesLexicograficas(Set<Relationship<Word>> relacionesLexicograficas) {
        this.relacionesLexicograficas = relacionesLexicograficas;
    }

    public Set<Word> getPalabras() {
        return palabras;
    }

    public void setPalabras(Set<Word> palabras) {
        this.palabras = palabras;
    }

    
    public Set<SynSet> relacionSemantica(String relacion, SynSet origen){
        Set<SynSet> lemas = new HashSet<>();
        for(Relationship<SynSet> rel : relacionesSemanticas){
            if(rel.getNombreDirecto().equals(relacion)){
                lemas.addAll(rel.getImagen(origen));
            } else if (rel.getNombreInversa().equals(relacion)){
                lemas.add(rel.getPreimagen(origen));
            }
        }
        return lemas;
    }
    
    
    public Set<Word> relacionLexicografica(String relacion, Word origen){
        Set<Word> palabras = new HashSet<>();
        for(Relationship<Word> rel : relacionesLexicograficas){
            if(rel.getNombreDirecto().equals(relacion)){
                palabras.addAll(rel.getImagen(origen));
            } else if (rel.getNombreInversa().equals(relacion)){
                palabras.add(rel.getPreimagen(origen));
            }
        }
        return palabras;
    }
}
