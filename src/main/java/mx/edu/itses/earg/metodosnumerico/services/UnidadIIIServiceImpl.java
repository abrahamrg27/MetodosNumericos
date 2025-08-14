package mx.edu.itses.earg.metodosnumerico.services;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.earg.metodosnumerico.domain.Gauss;
import mx.edu.itses.earg.metodosnumerico.domain.ReglaCramer;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnidadIIIServiceImpl implements UnidadIIIService{

    @Override
    public ReglaCramer AlgoritmoReglaCramer(ReglaCramer modelCramer) {
        // almacenamo los determianntes d la matriz
        ArrayList<Double> determinantes = new ArrayList<>();
        ArrayList<Double> vectorX = new ArrayList();

        //tamano del sistema lineal
        switch (modelCramer.getMN()) {
            case 2 -> {
                ArrayList<Double> A = modelCramer.getMatrizA();
                ArrayList<Double> b = modelCramer.getVectorB();

                double[][] MatrizA = {
                    {A.get(0), A.get(1)},
                    {A.get(2), A.get(3)}

                };

                determinantes.add(Det2(MatrizA));
                log.info("Det A " + determinantes.get(0));

                //Calculamos determinates para X1
                double[][] MatrizX1 = {
                    {b.get(0), A.get(1)},
                    {b.get(1), A.get(3)}

                };

                determinantes.add(Det2(MatrizX1));
                log.info("Matriz X1: " + determinantes.get(1));

                //Calculamos determinates para X2
                double[][] MatrizX2 = {
                    {A.get(0), b.get(0)},
                    {A.get(2), b.get(1)}

                };
                determinantes.add(Det2(MatrizX2));
                log.info("Matriz X2: " + determinantes.get(2));

                vectorX.add(determinantes.get(1) / determinantes.get(0));
                vectorX.add(determinantes.get(2) / determinantes.get(0));

            }
            
            case 3 -> {
                
                ArrayList<Double> A = modelCramer.getMatrizA(); 
                ArrayList<Double> b = modelCramer.getVectorB(); 


                double[][] MatrizA = {
                    {A.get(0), A.get(1), A.get(2)},
                    {A.get(3), A.get(4), A.get(5)},
                    {A.get(6), A.get(7), A.get(8)}
                };


                determinantes.add(Det3(MatrizA));
                log.info("Det A: " + determinantes.get(0));


                double[][] MatrizX1 = {
                    {b.get(0), A.get(1), A.get(2)},
                    {b.get(1), A.get(4), A.get(5)},
                    {b.get(2), A.get(7), A.get(8)}
                };
                determinantes.add(Det3(MatrizX1));
                log.info("Matriz X1: " + determinantes.get(1));


                double[][] MatrizX2 = {
                    {A.get(0), b.get(0), A.get(2)},
                    {A.get(3), b.get(1), A.get(5)},
                    {A.get(6), b.get(2), A.get(8)}
                };
                determinantes.add(Det3(MatrizX2));
                log.info("Matriz X2: " + determinantes.get(2));


                double[][] MatrizX3 = {
                    {A.get(0), A.get(1), b.get(0)},
                    {A.get(3), A.get(4), b.get(1)},
                    {A.get(6), A.get(7), b.get(2)}
                };
                determinantes.add(Det3(MatrizX3));
                log.info("Matriz X3: " + determinantes.get(3));


                vectorX.add(determinantes.get(1) / determinantes.get(0)); 
                vectorX.add(determinantes.get(2) / determinantes.get(0)); 
                vectorX.add(determinantes.get(3) / determinantes.get(0)); 
            }

        }
        modelCramer.setVectorX(vectorX);
        modelCramer.setDeterminantes(determinantes);
        return modelCramer;
    }

    private double Det2(double[][] A) {

        return A[0][0] * A[1][1] - A[0][1] * A[1][0];

    }
    private double Det3(double[][] c) {
    return c[0][0] * (c[1][1] * c[2][2] - c[1][2] * c[2][1])
         - c[0][1] * (c[1][0] * c[2][2] - c[1][2] * c[2][0])
         + c[0][2] * (c[1][0] * c[2][1] - c[1][1] * c[2][0]);
}

   @Override
public Gauss AlgoritmoGauss(Gauss modelGauss) {
    int n = modelGauss.getN();
    double[][] A = new double[n][n];
    double[] B = new double[n];
    
    for (int i = 0; i < n; i++) {
        B[i] = modelGauss.getVectorB().get(i);
        for (int j = 0; j < n; j++) {
            A[i][j] = modelGauss.getMatrizA().get(i * n + j);
        }
    }
    
    for (int k = 0; k < n - 1; k++) {
        for (int i = k + 1; i < n; i++) {
            double factor = A[i][k] / A[k][k];
            for (int j = k; j < n; j++) {
                A[i][j] -= factor * A[k][j];
            }
            B[i] -= factor * B[k];
        }
    }
    
    double[] X = new double[n];
    for (int i = n - 1; i >= 0; i--) {
        double suma = 0;
        for (int j = i + 1; j < n; j++) {
            suma += A[i][j] * X[j];
        }
        X[i] = (B[i] - suma) / A[i][i];
    }
    
    ArrayList<Double> solucionList = new ArrayList<>();
    for (double val : X) {
        solucionList.add(val);
    }
    modelGauss.setSolucion(solucionList);
    
    return modelGauss;
}

}