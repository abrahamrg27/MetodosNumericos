package mx.edu.itses.earg.metodosnumerico.services;

import static java.lang.Math.abs;
import org.mariuszgromada.math.mxparser.*;

public class Funciones {

    public static double Ecuacion(String f1, double x) {
        String funcionDefinida;
        if (!f1.trim().startsWith("f(")) {
            funcionDefinida = "f(x) = " + f1;
        } else {
            funcionDefinida = f1;
        }

        Function funcion = new Function(funcionDefinida);
        Expression evaluacion = new Expression("f(" + x + ")", funcion);
        double f = evaluacion.calculate();

        if (Double.isNaN(f)) {
            throw new IllegalArgumentException("La función no se pudo evaluar correctamente. Verifica la expresión: " + f1);
        }

        return f;
    }
    
    public static double ErrorRelativo(double ValorNuevo, double ValorAnterior) {
        return abs((ValorNuevo - ValorAnterior) / ValorNuevo * 100);
    }
}
