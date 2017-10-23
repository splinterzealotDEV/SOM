import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class testPunto {
    public static void main(String[] args) {
        Punto[] puntos=new Punto[8];
        Punto[] centroids=new Punto[puntos.length];
        Punto centro;
        int radio;
        Punto p=new Punto();
        puntos=p.llenarPuntos(puntos);
        p.imprimir(puntos);
        centro=p.calcularCentro(puntos);
        radio=p.calcularRadio(centro,puntos);
        System.out.println(radio);
        //centroids=puntos;
        for (int i=0;i<centroids.length;i++)
        {
            centroids[i]=new Punto();
        }
        centroids=p.ponerCentroides(centroids,centro,radio);
        //System.out.println("originales");
        //p.imprimir(centroids);

        p.aprender(puntos,centroids);
        p.imprimir(centroids);
        p.encontrarRuta(centroids);

    }
}
