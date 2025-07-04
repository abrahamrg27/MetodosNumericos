package mx.edu.itses.earg.metodosnumerico.domain;

import lombok.Data;

@Data
public class SecanteModificada {
    private String fx;
    private double xi;
    private double h;
    private double ea;
    private int iteracionesMaximas;

    private double fxi;
    private double dfxi;
    private double xii;
    private double error;
    private int iteracion;
}
