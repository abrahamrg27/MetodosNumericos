package mx.edu.itses.earg.MetodosNumericos.services;

import java.util.ArrayList;
import mx.edu.itses.earg.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.earg.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.earg.MetodosNumericos.domain.ReglaFalsa;

public interface UnidadIIservices {
    public ArrayList<Biseccion> AlgoritmoBiseccion(Biseccion biseccion);

    public ArrayList<ReglaFalsa> AlgoritmoReglaFalsa(ReglaFalsa reglafalsa);
   
    public ArrayList<PuntoFijo> AlgoritmoPuntoFijo(PuntoFijo puntofijo);

  
}
