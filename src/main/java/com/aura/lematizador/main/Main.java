/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aura.lematizador.main;

//import java.io.BufferedReader;
import com.aura.lematizador.lematizador.Lematizador;
import com.aura.lematizador.lematizador.SynSet;
import com.aura.lematizador.lematizador.Word;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


/**
 *
 * @author diana
 */
public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException{
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));   
        //String cfgPath = in.readLine();
        String cfgPath = args[0];
        File cfgFile = new File("resources/diccionarioSimple.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = (Document) dBuilder.parse(cfgFile);
        
//Inicializo todas las relaciones.
        Lematizador lem = new Lematizador(doc);
        for(SynSet s : lem.encontrarLema(new Word("claro"))){
            System.out.println(s.getLema());
        };
        
        for(Word s : lem.relacionLexicografica("antonimo", new Word("Claro"))){
            System.out.println(s.getPalabra());
        }
    }
    

}
