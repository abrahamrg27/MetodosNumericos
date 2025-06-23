package mx.edu.itses.earg.MetodosNumericos.domain;

import lombok.Data;

@Data   
public class PuntoFijo {
    private String FX;         // Función g(x) como cadena
    private double Xi;         // Valor actual de xi
    private double GXi;        // Valor de g(xi)
    private double Ea;         // Error aproximado relativo
    private int iteracion;     // Número de la iteración
    private int iteracionesMaximas; 
}
