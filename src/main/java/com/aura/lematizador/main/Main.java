/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aura.lematizador.main;

//import java.io.BufferedReader;
import com.aura.lematizador.lematizador.Relationship;
import com.aura.lematizador.lematizador.SynSet;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *
 * @author diana
 */
public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException{
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));   
        //String cfgPath = in.readLine();
        for(int i=0;i<args.length;i++){
            System.out.println(args[i]);
        }
        String cfgPath = args[0];
        File cfgFile = new File(cfgPath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = (Document) dBuilder.parse(cfgFile);
        //Inicializo todas las relaciones.
        NodeList relaciones = doc.getElementsByTagName("relacion");
        Set<Relationship> listaRelaciones = new HashSet<>();
        for(int i = 0; i < relaciones.getLength(); i++){
            Node relacion = relaciones.item(i);
            NamedNodeMap atributos = relacion.getAttributes();
            Node directa = atributos.getNamedItem("directa");
            Node inversa = atributos.getNamedItem("inversa");
            System.out.println("Directa: " + directa.getNodeValue() + " Ïnversa: " + inversa.getNodeValue());
            Relationship rel = new Relationship(directa.getNodeValue(), inversa.getNodeValue());
            listaRelaciones.add(rel);
        }
        //Inicializo todos los SynSets
        NodeList synsetlist = doc.getElementsByTagName("synset");
        Set<SynSet> synsets = new HashSet<>();
        for(int i = 0; i < synsetlist.getLength();i++){
            Node synsetNode = synsetlist.item(i);
            
        }
    }
    

}
