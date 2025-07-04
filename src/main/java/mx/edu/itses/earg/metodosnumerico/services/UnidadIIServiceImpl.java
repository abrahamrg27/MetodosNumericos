package mx.edu.itses.earg.metodosnumerico.services;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.earg.metodosnumerico.domain.Biseccion;
import mx.edu.itses.earg.metodosnumerico.domain.NewtonRaphson;
import mx.edu.itses.earg.metodosnumerico.domain.PuntoFijo;
import mx.edu.itses.earg.metodosnumerico.domain.ReglaFalsa;
import mx.edu.itses.earg.metodosnumerico.domain.Secante;
import mx.edu.itses.earg.metodosnumerico.domain.SecanteModificada;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnidadIIServiceImpl implements UnidadIIservices {

    @Override
    public ArrayList<Biseccion> AlgoritmoBiseccion(Biseccion biseccion){
        ArrayList<Biseccion> respuesta = new ArrayList<>();
        double XL, XU, XRa, XRn, FXL, FXU, FXR, Ea;

        XL = biseccion.getXL();
        XU = biseccion.getXU();
        XRa = 0;
        Ea = 100;
        
        FXL = Funciones.Ecuacion(biseccion.getFX(), XL);
        FXU = Funciones.Ecuacion(biseccion.getFX(), XU);
        if (FXL * FXU < 0) {
            for (int i = 1; i <= biseccion.getIteracionesMaximas(); i++) {
                XRn = (XL + XU) / 2;
                FXL = Funciones.Ecuacion(biseccion.getFX(), XL);
                FXU = Funciones.Ecuacion(biseccion.getFX(), XU);
                FXR = Funciones.Ecuacion(biseccion.getFX(), XRn);
                if (i != 1) {
                    Ea = Funciones.ErrorRelativo(XRn, XRa);
                }
                Biseccion renglon = new Biseccion();
                renglon.setXL(XL);
                renglon.setXU(XU);
                renglon.setXR(XRn);
                renglon.setFXL(FXL);
                renglon.setFXU(FXU);
                renglon.setFXR(FXR);
                renglon.setEa(Ea);
                if (FXL * FXR < 0) {
                    XU = XRn;
                } else if (FXL * FXR > 0) {
                    XL = XRn;
                } else if (FXL * FXR == 0) {
                    break;
                }
                XRa = XRn;
                respuesta.add(renglon);
                if (Ea <= biseccion.getEa()) {
                    break;
                }
            }
        } else {
            Biseccion renglon = new Biseccion();
          
            respuesta.add(renglon);
        }

        return respuesta;
    }

    @Override
    public ArrayList<ReglaFalsa> AlgoritmoReglaFalsa(ReglaFalsa reglafalsa) {
         ArrayList<ReglaFalsa> respuestaReglaFalsa = new ArrayList<>();
    double XL, XU, XRa = 0, XRn, FXL, FXU, FXR, Ea = 100;
    XL = reglafalsa.getXL();
    XU = reglafalsa.getXU();
    
    FXL = Funciones.Ecuacion(reglafalsa.getFX(), XL);
    FXU = Funciones.Ecuacion(reglafalsa.getFX(), XU);
    
    if (FXL * FXU < 0) {
        for (int i = 1; i <= reglafalsa.getIteracionesMaximas(); i++) {
            // Fórmula de Regla Falsa
            XRn = XU - (FXU * (XL - XU)) / (FXL - FXU);
            FXR = Funciones.Ecuacion(reglafalsa.getFX(), XRn);
            
            if (i != 1) {
                Ea = Funciones.ErrorRelativo(XRn, XRa);
            }
            
            ReglaFalsa renglon = new ReglaFalsa();
            renglon.setIteracionesMaximas(i);
            renglon.setXL(XL);
            renglon.setXU(XU);
            renglon.setXR(XRn);
            renglon.setFXL(FXL);
            renglon.setFXU(FXU);
            renglon.setFXR(FXR);
            renglon.setEa(Ea);
            
            // AGREGAR ESTA LÍNEA PARA CALCULAR EL PRODUCTO
            renglon.setProductoFXL_FXR(FXL * FXR);
            
            if (FXL * FXR < 0) {
                XU = XRn;
                FXU = FXR;
            } else {
                XL = XRn;
                FXL = FXR;
            }
            
            XRa = XRn;
            respuestaReglaFalsa.add(renglon);
            
            if (Ea <= reglafalsa.getEa()) {
                break;
            }
        }
    } else {
        respuestaReglaFalsa.add(new ReglaFalsa());
    }
    return respuestaReglaFalsa;
}
    @Override
    public ArrayList<PuntoFijo> AlgoritmoPuntoFijo(PuntoFijo puntofijo) {
    ArrayList<PuntoFijo> respuestaPuntoFijo = new ArrayList<>();
    double Xi, XiAnterior = 0, GXi, Ea = 100;

    Xi = puntofijo.getXi();

    for (int i = 1; i <= puntofijo.getIteracionesMaximas(); i++) {

        GXi = Funciones.Ecuacion(puntofijo.getFx(), Xi);

        if (i != 1) {
            Ea = Funciones.ErrorRelativo(GXi, XiAnterior);
        }

        PuntoFijo renglon = new PuntoFijo();
        renglon.setIteracion(i);
        renglon.setXi(Xi);
        renglon.setGXi(GXi);
        renglon.setEa(i == 1 ? 0 : Ea);

        respuestaPuntoFijo.add(renglon);

        if (i > 1 && Ea <= puntofijo.getEa()) {
            break;
        }

        XiAnterior = Xi;
        Xi = GXi;
    }

    return respuestaPuntoFijo;
}
    @Override
    public ArrayList<NewtonRaphson> AlgoritmoNewtonRaphson(NewtonRaphson newtonRaphson) {
    ArrayList<NewtonRaphson> respuestaNewtonRaphson = new ArrayList<>();
    double Xi, Xii = 0, Xia = 0, FXi, FXii, Ea = 100;
    
    Xi = newtonRaphson.getXi();
    
    FXii = Funciones.Ecuacion(newtonRaphson.getFXDerivada(), Xi);
    if (Math.abs(FXii) < 1e-10) {
        NewtonRaphson error = new NewtonRaphson();
        error.setIteracionesMaximas(0);
        error.setXi(Xi);
        error.setEa(-1);
        respuestaNewtonRaphson.add(error);
        return respuestaNewtonRaphson;
    }
    
    for (int i = 1; i <= newtonRaphson.getIteracionesMaximas(); i++) {
        FXi = Funciones.Ecuacion(newtonRaphson.getFX(), Xi);
        
        FXii = Funciones.Ecuacion(newtonRaphson.getFXDerivada(), Xi);
        
        if (Math.abs(FXii) < 1e-10) {
            break;        }
  
        Xii = Xi - (FXi / FXii);
        
        if (i != 1) {
            Ea = Funciones.ErrorRelativo(Xii, Xia);
        }

        NewtonRaphson renglon = new NewtonRaphson();
        renglon.getIteracionesMaximas();
        renglon.setXi(Xi);
        renglon.setFXi(FXi);
        renglon.setFXii(FXii);
        renglon.setXii(Xii);
        renglon.setEa(Ea);

        respuestaNewtonRaphson.add(renglon);
  
        if (i > 1 && Ea <= newtonRaphson.getEa()) {
            break;
        }
        
        Xia = Xi;
        Xi = Xii;
    }
    
    return respuestaNewtonRaphson;
}  
    @Override
    public ArrayList<Secante> AlgoritmoSecante(Secante secante) {
    ArrayList<Secante> resultados = new ArrayList<>();
    
    String funcionCorregida = secante.getFX().replace('–', '-').replace("−", "-");
    
    double xi = secante.getXi();
    double xi1 = secante.getXiMenos1();
    double ea = secante.getEa();
    int max = secante.getIteracionesMaximas();

    for (int i = 0; i < max; i++) {
        double fxi = Funciones.Ecuacion(funcionCorregida, xi);
        double fxi1 = Funciones.Ecuacion(funcionCorregida, xi1);

        if ((fxi1 - fxi) == 0) break;

        double xii = xi - (fxi * (xi1 - xi)) / (fxi1 - fxi);
        double error = Math.abs((xii - xi) / xii) * 100;

        Secante paso = new Secante();
        paso.setXi(xi);
        paso.setXiMenos1(xi1);
        paso.setFXi(fxi);
        paso.setFXiMenos1(fxi1);
        paso.setXii(xii);
        paso.setError(error);

        resultados.add(paso);

        if (error < ea) break;

        xi1 = xi;
        xi = xii;
    }

    return resultados;
}
 @Override
    public ArrayList<SecanteModificada> AlgoritmoSecanteModificada(SecanteModificada secantemodificada) {
    ArrayList<SecanteModificada> resultado = new ArrayList<>();
    double xi = secantemodificada.getXi();
    double h = secantemodificada.getH();
    double ea = secantemodificada.getEa();
    int max = secantemodificada.getIteracionesMaximas();

    double xAnt = 0;
    double error = 100;

    for (int i = 1; i <= max; i++) {
        double fxi = Funciones.Ecuacion(secantemodificada.getFx(), xi);
        double dfxi = (Funciones.Ecuacion(secantemodificada.getFx(), xi + h) - fxi) / h;

        if (Math.abs(dfxi) < 1e-12) break;

        double xii = xi - (fxi / dfxi);
        if (i > 1) error = Funciones.ErrorRelativo(xii, xAnt);

        SecanteModificada paso = new SecanteModificada();
        paso.setIteracion(i);
        paso.setXi(xi);
        paso.setFxi(fxi);
        paso.setDfxi(dfxi);
        paso.setXii(xii);
        paso.setError(i == 1 ? 0 : error);
        resultado.add(paso);

        if (error < ea) break;

        xAnt = xii;
        xi = xii;
    }
    return resultado;
}
}