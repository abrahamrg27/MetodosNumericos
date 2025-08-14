package mx.edu.itses.earg.metodosnumerico.domain;

import java.util.ArrayList;
import lombok.Data;

@Data
public class Gauss {
    private int n;
    private ArrayList<Double> matrizA;
    private ArrayList<Double> vectorB;
    private ArrayList<Double> solucion;
    private int numeroRenglon;

    public int incrementarRenglon() {
        return numeroRenglon++;
    }
}
