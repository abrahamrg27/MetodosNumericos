package mx.edu.itses.earg.metodosnumerico.services;

import java.util.ArrayList;
import mx.edu.itses.earg.metodosnumerico.domain.Biseccion;
import mx.edu.itses.earg.metodosnumerico.domain.NewtonRaphson;
import mx.edu.itses.earg.metodosnumerico.domain.PuntoFijo;
import mx.edu.itses.earg.metodosnumerico.domain.ReglaFalsa;

public interface UnidadIIservices {
    public ArrayList<Biseccion> AlgoritmoBiseccion(Biseccion biseccion);

    public ArrayList<ReglaFalsa> AlgoritmoReglaFalsa(ReglaFalsa reglafalsa);
   
    public ArrayList<PuntoFijo> AlgoritmoPuntoFijo(PuntoFijo puntofijo);

    public ArrayList<NewtonRaphson>AlgoritmoNewtonRaphson(NewtonRaphson newtonRaphson);
}
