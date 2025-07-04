package mx.edu.itses.earg.metodosnumerico.domain;

import lombok.Data;

@Data
public class NewtonRaphson {
     private String FX;
    private String FXDerivada;
    private double Xi;
    private double FXi;
    private double FXii;
    private double Xii;
    private double Ea;
    private int IteracionesMaximas;
}
