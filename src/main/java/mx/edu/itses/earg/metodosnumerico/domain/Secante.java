package mx.edu.itses.earg.metodosnumerico.domain;

import lombok.Data;

@Data
public class Secante {
    private String FX;
    private double Xi;
    private double XiMenos1;
    private double FXi;
    private double FXiMenos1;
    private double Xii;
    private double Error;
    private double Ea;
    private int IteracionesMaximas;
}