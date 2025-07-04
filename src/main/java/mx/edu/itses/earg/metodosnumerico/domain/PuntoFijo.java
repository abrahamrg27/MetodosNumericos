package mx.edu.itses.earg.metodosnumerico.domain;

import lombok.Data;

@Data   
public class PuntoFijo {
    private String Fx;         // Función g(x) como cadena
    private double Xi;         // Valor actual de xi
    private double GXi;        // Valor de g(xi)
    private double Ea;         // Error aproximado relativo
    private int iteracion;     // Número de la iteración
    private int iteracionesMaximas; 
}
