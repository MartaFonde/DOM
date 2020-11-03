package app;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Func1DOM {

    public Document creaArbol(String ruta) {
        Document doc = null;
        try {
            DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
            factoria.setIgnoringComments(true);
            DocumentBuilder builder = factoria.newDocumentBuilder();
            doc = builder.parse(ruta);
        } catch (Exception e) {
            System.out.println("Erro xerando a árbore DOM: " + e.getMessage());
        }
        return doc;
    }

    //2. Muestra titulos peliculas
    public void titulos(Document doc) {
        Node filmoteca, pelicula, dato;
        NodeList peliculas, datosPelicula;

        filmoteca = doc.getFirstChild();
        peliculas = filmoteca.getChildNodes();
        for (int i = 0; i < peliculas.getLength(); i++) {
            pelicula = peliculas.item(i);
            datosPelicula = pelicula.getChildNodes();
            for (int j = 0; j < datosPelicula.getLength(); j++) {
                dato = datosPelicula.item(j);
                if (dato.getNodeName().equals("titulo")) {
                    System.out.println(dato.getFirstChild().getNodeValue());
                }
            }
        }
    }

    public void titulosTag(Document doc) {
        NodeList titulos;
        Node titulo;

        titulos = doc.getElementsByTagName("titulo");
        for (int i = 0; i < titulos.getLength(); i++) {
            titulo = titulos.item(i);
            System.out.println(titulo.getFirstChild().getNodeValue());
        }
    }

    //3. Muestra titulos, directores (nombre y apellidos) y género
    public void ejer3(Document doc) {
        Node filmoteca, pelicula, datoPelicula, datosDirector;
        NodeList peliculas, datosPelicula, nodeListAux;

        filmoteca = doc.getFirstChild();
        peliculas = filmoteca.getChildNodes();

        for (int i = 0; i < peliculas.getLength(); i++) {
            pelicula = peliculas.item(i);
            datosPelicula = pelicula.getChildNodes();

            for (int j = 0; j < datosPelicula.getLength(); j++) { 
                datoPelicula = datosPelicula.item(j);
                if (datoPelicula.getNodeName().equals("titulo")) { 
                    System.out.println(datoPelicula.getFirstChild().getNodeValue());
                }
                if (datoPelicula.getNodeName().equals("director")) {
                    if (datoPelicula.hasChildNodes()) { 
                        nodeListAux = datoPelicula.getChildNodes();
                        for (int k = 0; k < nodeListAux.getLength(); k++) {
                            datosDirector = nodeListAux.item(k);
                            if (datosDirector.getNodeType() == Node.ELEMENT_NODE) {
                                System.out.print(datosDirector.getFirstChild().getNodeValue()+" ");
                            }
                        }
                        System.out.println();
                    }
                }
            }
            if (pelicula.hasAttributes()) {
                Element e = (Element)pelicula;
                System.out.println(e.getAttribute("genero")+"\n");
            }
        }
    }

    public void ejer3Tag(Document doc) {
        NodeList peliculas, directores;
        Node pelicula;

        peliculas = doc.getElementsByTagName("pelicula");
        for (int i = 0; i < peliculas.getLength(); i++) {
            pelicula = peliculas.item(i);
            System.out.println(getDato(pelicula, "titulo"));
            directores = ((Element) pelicula).getElementsByTagName("director");
            for (int j = 0; j < directores.getLength(); j++) {
                System.out.println(getDato(directores.item(j), "nombre") + " " + getDato(directores.item(j), "apellido"));
            }
            if (pelicula.hasAttributes()) {
                Element e = (Element) pelicula;
                System.out.println(e.getAttribute("genero")+"\n");
            }
        }
    }

    public String getDato(Node nodo, String tag) {
        Element e = (Element) nodo;
        NodeList nList = e.getElementsByTagName(tag);
        if (nList.getLength() > 0) { 
            return nList.item(0).getFirstChild().getNodeValue(); 
        }
        return "";
    }

    //5. Muestra titulo de pelis que tienen más de n directores
    public void numDirectores(Document doc, int n) {
        Node filmoteca, peli, dato;
        NodeList peliculas, datosPeli;
        int cont = 0;
        String titulo = "";

        filmoteca = doc.getFirstChild();
        peliculas = filmoteca.getChildNodes();

        for (int i = 0; i < peliculas.getLength(); i++) {
            peli = peliculas.item(i);
            if (peli.getNodeType() == Node.ELEMENT_NODE) {
                if (peli.hasChildNodes()) {
                    datosPeli = peli.getChildNodes(); 

                    for (int j = 0; j < datosPeli.getLength(); j++) { 
                        dato = datosPeli.item(j);
                        if (dato.getNodeName().equals("titulo")) {
                            titulo = dato.getFirstChild().getNodeValue();
                        }
                        if (dato.getNodeName().equals("director")) {
                            cont++;
                        }
                    }
                    if (cont > n) {
                        System.out.println(titulo);                         
                    }
                }
            }
            cont = 0;
        }
    }

    public void numDirectoresTag(Document doc, int n) {
        NodeList pelis;
        Node peli;
        NodeList directores;

        pelis = doc.getElementsByTagName("pelicula");
        for (int i = 0; i < pelis.getLength(); i++) {
            peli = pelis.item(i);
            directores = ((Element) peli).getElementsByTagName("director");
            if (directores.getLength() > n) {
                System.out.println(getDato(peli, "titulo"));
            }
        }
    }

    //6. Devuelve una lista con los diferentes géneros que existen en la filmoteca
    public ArrayList<String> generos(Document doc) {
        Node filmoteca, peli;
        NodeList pelis;
        ArrayList<String> generos = new ArrayList<>();

        filmoteca = doc.getFirstChild();
        pelis = filmoteca.getChildNodes();

        for (int i = 0; i < pelis.getLength(); i++) {
            peli = pelis.item(i);
            if (peli.getNodeName().equals("pelicula")) {
                Element e = (Element) peli;
                String genero = e.getAttribute("genero");
                if (!generos.contains(genero)) {
                    generos.add(genero);
                }
            }
        }
        return generos;
    }

    public void arbolRecursivo(Node n) {
        Node nodo;
        NodeList nList;
        // char tab = '\t';
        // int cont = 0;

        nList = n.getChildNodes();
        for (int i = 0; i < nList.getLength(); i++) {
            nodo = nList.item(i);

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                System.out.print("1 ");
            } else if (nodo.getNodeType() == Node.TEXT_NODE) {
                System.out.print("3 ");
            } else if (nodo.getNodeType() == Node.COMMENT_NODE) {
                System.out.print("8 ");
            }
            System.out.println(nodo.getNodeName());

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                arbolRecursivo(nodo);
                // cont++;
            }
        }
    }
}
