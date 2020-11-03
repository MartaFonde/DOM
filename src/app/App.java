package app;

import org.w3c.dom.Document;

public class App {
    public static void main(String[] args) throws Exception {
        Func1DOM f1 = new Func1DOM();
        Func2DOM f2 = new Func2DOM();
        String ruta = "src/datos/peliculas.xml";
        String salida = "src/datos/peliculas2.xml";
        Document doc = f1.creaArbol(ruta); // carga doc en memoria

        // f1.titulos(doc);
        // f1.titulosTag(doc);
        // f1.ejer3(doc);
        // f1.ejer3Tag(doc);
        // f1.numDirectores(doc, 1);
        // f1.numDirectoresTag(doc, 1);
        // ArrayList<String> gen = f1.generos(doc);
        // System.out.println("Número géneros diferentes: " + gen.size() + gen.toString());
        // f1.arbolRecursivo(doc);

        // f2.añadirAtributo(doc, "Dune", "nota", "9");
        // f2.grabarDOM(doc, ruta); //si usamos ruta modif xml original
        // f2.eliminarAtributo(doc, "Dune", "nota");
        // f2.grabarDOM(doc, ruta);
        // f2.añadirPelicula(doc, "Depredator", "John", "Tiernan", "1987", "Acción", "VO");
        // f2.grabarDOM(doc, salida);
        // f2.modificarNombreDirector(doc, "Matrix", "Larry", "Lana");
        // f2.grabarDOM(doc, salida);
        // f2.añadirDirector(doc, "Dune", "Alfredo", "Landa");
        // f2.grabarDOM(doc, salida);
        // f2.eliminarPelicula(doc, "Matrix");
        // f2.grabarDOM(doc, salida);
        // String salida2 = "src/datos/compañia.xml";
        // f2.grabarDOM(f2.nuevoDOM("Juan", "López Pérez", "Juanín", "1000"), salida2);
    }
}
