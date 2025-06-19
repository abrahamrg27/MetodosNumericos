package mx.edu.itses.earg.MetodosNumericos.services;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.earg.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.earg.MetodosNumericos.domain.ReglaFalsa;
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
    
}

