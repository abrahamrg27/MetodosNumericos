package mx.edu.itses.earg.metodosnumerico.services;

import mx.edu.itses.earg.metodosnumerico.domain.Gauss;
import mx.edu.itses.earg.metodosnumerico.domain.ReglaCramer;

public interface UnidadIIIService {


    public ReglaCramer AlgoritmoReglaCramer(ReglaCramer modelCramer);
    
    public Gauss AlgoritmoGauss(Gauss modelGauss);

}
