package app;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
    
public class Func2DOM{

    //7a. Dado titulo de peli, añade, si no existe, un atributo 
    public void añadirAtributo(Document doc, String titulo, String atribClave, String atribValor) {
        NodeList titulos;
        Element e;
        titulos = doc.getElementsByTagName("titulo");
        try{
            for (int i = 0; i < titulos.getLength(); i++) {
                if (titulos.item(i).getFirstChild().getNodeValue().equals(titulo)) {
                    e = (Element) titulos.item(i).getParentNode(); 
                    if (!e.hasAttribute(atribClave)) {
                        e.setAttribute(atribClave, atribValor);
                    }
                }
            }
        }catch(DOMException ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }

    //7b. Dado titulo y atributo, si existe lo elimina
    public void eliminarAtributo(Document doc, String titulo, String atrib) {
        NodeList titulos;
        Element e;
        titulos = doc.getElementsByTagName("titulo");
        try{
            for (int i = 0; i < titulos.getLength(); i++) {
                if (titulos.item(i).getFirstChild().getNodeValue().equals(titulo)) {
                    e = (Element) titulos.item(i).getParentNode(); 
                    if (e.hasAttribute(atrib)) {
                        e.removeAttribute(atrib);
                        ;
                    }
                }
            }
        }catch(DOMException ex){
            System.out.println("Error: "+ex.getMessage());
        }        
    }

    //Añade pelicula a nodo raiz filmoteca
    public void añadirPelicula(Document doc, String titulo, String nombre, String apellido, String ano,
            String genero, String idioma) {
        try {
            Element nodoPelicula = doc.createElement("Pelicula");
            nodoPelicula.setAttribute("año", ano);
            nodoPelicula.setAttribute("genero", genero);
            nodoPelicula.setAttribute("idioma", "VO");
            nodoPelicula.appendChild(doc.createTextNode("\n"));

            Element nodoTitulo = doc.createElement("titulo");
            Text textNodoTitulo = doc.createTextNode(titulo);
            nodoTitulo.appendChild(textNodoTitulo);
            nodoPelicula.appendChild(nodoTitulo);
            nodoPelicula.appendChild(doc.createTextNode("\n"));

            Element nodoDirector = doc.createElement("director");
            Element nombreDirector = doc.createElement("nombre");
            Text textNombreDirector = doc.createTextNode(nombre);
            nombreDirector.appendChild(textNombreDirector);
            nodoDirector.appendChild(nombreDirector);
            Element apellidoDirector = doc.createElement("apellido");
            Text textApellidoDirector = doc.createTextNode(apellido);
            apellidoDirector.appendChild(textApellidoDirector);
            nodoDirector.appendChild(apellidoDirector);
            nodoPelicula.appendChild(nodoDirector);
            nodoPelicula.appendChild(doc.createTextNode("\n"));

            Node raiz = doc.getFirstChild();
            raiz.appendChild(nodoPelicula);
        } catch (DOMException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //9. Modifica el nombre de un director de pelicula
    public void modificarNombreDirector(Document doc, String titulo, String nombreViejo, String nombreNuevo) {
        Node pelicula, nombreDirector;
        NodeList datosPelicula, datosDirector;
        NodeList titulos = doc.getElementsByTagName("titulo");
        
        try{
            for (int i = 0; i < titulos.getLength(); i++) {
                if (titulos.item(i).getFirstChild().getNodeValue().equals(titulo)) {
                    pelicula = (Element) titulos.item(i).getParentNode();
                    datosPelicula = pelicula.getChildNodes();

                    for (int j = 0; j < datosPelicula.getLength(); j++) {
                        if (datosPelicula.item(j).getNodeName().equals("director")) {
                            datosDirector = datosPelicula.item(j).getChildNodes();
                            for (int k = 0; k < datosDirector.getLength(); k++) {
                                if (datosDirector.item(k).getNodeName().equals("nombre")) {
                                    if (datosDirector.item(k).getFirstChild().getNodeValue().equals(nombreViejo)) {
                                        nombreDirector = datosDirector.item(k);
                                        Text textNombreDirector = doc.createTextNode(nombreNuevo);
                                        Element nuevoNombreDirector = doc.createElement("nombre");
                                        nuevoNombreDirector.appendChild(textNombreDirector);
                                        datosPelicula.item(j).replaceChild(nuevoNombreDirector, nombreDirector);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }catch (DOMException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //10. Añade director a pelicula
    public void añadirDirector(Document doc, String titulo, String nombre, String apellido) {
        NodeList titulos = doc.getElementsByTagName("titulo");

        try{
            for (int i = 0; i < titulos.getLength(); i++) {
                if (titulos.item(i).getFirstChild().getNodeValue().equals(titulo)) {
                    Element e = (Element) titulos.item(i).getParentNode();
    
                    Element director = doc.createElement("director");
                    Element nombreDirector = doc.createElement("nombre");
                    Text textNombreDirector = doc.createTextNode(nombre);
                    nombreDirector.appendChild(textNombreDirector);
                    Element apellidoDirector = doc.createElement("apellido");
                    Text textApellidoDirector = doc.createTextNode(apellido);
                    apellidoDirector.appendChild(textApellidoDirector);
                    director.appendChild(nombreDirector);
                    director.appendChild(apellidoDirector);
                    e.appendChild(director);
                    e.appendChild(doc.createTextNode("\n"));
                }
            }
        }catch (DOMException e) {
            System.out.println("Error: " + e.getMessage());
        }        
    }

    //11. Elimina pelicula por titulo
    public void eliminarPelicula(Document doc, String titulo) {
        NodeList titulos = doc.getElementsByTagName("titulo");
        try{
            for (int i = 0; i < titulos.getLength(); i++) {
                if (titulos.item(i).getFirstChild().getNodeValue().equals(titulo)) {
                    Element e = (Element) titulos.item(i).getParentNode().getParentNode();
                    e.removeChild(titulos.item(i).getParentNode());
                }
            }
        }catch (DOMException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void grabarDOM(Document document, String ficheroSalida)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
        DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
        DOMImplementationLS ls = (DOMImplementationLS) registry.getDOMImplementation("XML 3.0 LS 3.0");
        LSOutput output = ls.createLSOutput();
        output.setEncoding("UTF-8");
        output.setByteStream(new FileOutputStream(ficheroSalida));
        LSSerializer serializer = ls.createLSSerializer();
        serializer.setNewLine("\r\n");            
        serializer.getDomConfig().setParameter("format-pretty-print", true);
        serializer.write(document, output);
    }

    //12. Crea nuevo doc XML
    public Document nuevoDOM(String nombre, String apellidos, String apodo, String salario) {
        Document doc = null;

        try {
            DocumentBuilderFactory fábricaCreadorDocumento = DocumentBuilderFactory.newInstance();
            DocumentBuilder creadorDocumento = fábricaCreadorDocumento.newDocumentBuilder();
            doc = creadorDocumento.newDocument();

            Element nodoCompañia = doc.createElement("compañia");
            doc.appendChild(nodoCompañia);

            Element nodoEmpleado = doc.createElement("empleado");
            nodoEmpleado.setAttribute("id", "1");
            
            Element nodoNombre = doc.createElement("nombre");
            Text textNombre = doc.createTextNode(nombre);
            nodoNombre.appendChild(textNombre);
            nodoEmpleado.appendChild(nodoNombre);

            Element nodoApellidos = doc.createElement("apellidos");
            Text textApellidos = doc.createTextNode(apellidos);
            nodoApellidos.appendChild(textApellidos);            
            nodoEmpleado.appendChild(nodoApellidos);

            Element nodoApodo = doc.createElement("apodo");
            Text textApodo = doc.createTextNode(apodo);
            nodoApodo.appendChild(textApodo);
            nodoEmpleado.appendChild(nodoApodo);

            Element nodoSalario = doc.createElement("salario");
            Text textSalario = doc.createTextNode(salario);
            nodoSalario.appendChild(textSalario);
            nodoEmpleado.appendChild(nodoSalario);

            nodoCompañia.appendChild(nodoEmpleado);

        }catch (DOMException e) {
            System.out.println("Error: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("Erro xerando a árbore DOM: " + e.getMessage());
        }
        return doc;
    }
}
    