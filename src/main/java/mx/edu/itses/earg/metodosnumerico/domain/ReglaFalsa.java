package mx.edu.itses.earg.metodosnumerico.domain;

import lombok.Data;

@Data   
public class ReglaFalsa {
      private String FX;// funcion a ecvaluar
       private double XL;
    private double XU;
    private double XR;
    private double FXL;
    private double FXU;
    private double FXR;
    private double ProductoFXL_FXR;
    private double Ea;
    private int IteracionesMaximas;
}
