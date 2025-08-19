package mx.edu.itses.earg.metodosnumerico.domain;

import java.util.ArrayList;
import lombok.Data;

@Data
public class GaussJordan {
    private int n;
    private ArrayList<Double> matrizA;
    private ArrayList<Double> vectorB;
    private ArrayList<Double> solucion;
    private ArrayList<ArrayList<Double>> pasos;

}
