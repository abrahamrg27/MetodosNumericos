package mx.edu.itses.earg.metodosnumerico.web;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.earg.metodosnumerico.domain.Gauss;
import mx.edu.itses.earg.metodosnumerico.domain.GaussJordan;
import mx.edu.itses.earg.metodosnumerico.domain.ReglaCramer;
import mx.edu.itses.earg.metodosnumerico.services.UnidadIIIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class Unit3Controller {
    @Autowired    
    private UnidadIIIService unidadIIIsrv;
    
    
    @GetMapping("/unit3")
    public String index(Model model) {
        return "unit3/index/index_u3";
    }
    
    @GetMapping("/unit3/formcramer")
    public String formCramer(Model model) {
        ReglaCramer modelCramer = new ReglaCramer();
        model.addAttribute("modelCramer",modelCramer);
        return "unit3/cramer/formcramer";
    }
    
    @PostMapping("/unit3/solvecramer")
    public String solveCramer(ReglaCramer modelCramer,Errors errores,Model model) {
        log.info("OBJECTOS:" +  modelCramer);
        ArrayList<Double> A = modelCramer.getMatrizA();
        var solveCramer = unidadIIIsrv.AlgoritmoReglaCramer(modelCramer);
        log.info("Determinantes: " + solveCramer.getDeterminantes());
        log.info("Solución: " + solveCramer.getVectorX());
       model.addAttribute("solveCramer",solveCramer);
       return "unit3/cramer/formcramer";
        
    }
    @GetMapping("/unit3/gauss")
public String formGauss(Model model) {
    model.addAttribute("gauss", new Gauss());
    return "unit3/gauss/formgauss";
}

@PostMapping("/unit3/gauss/solvegauss")
public String resultadoGauss(@RequestParam int n,
                              @RequestParam("matrizA") List<Double> matrizA,
                              @RequestParam("vectorB") List<Double> vectorB,
                              Model model) {
    Gauss gauss = new Gauss();
    gauss.setN(n);
    gauss.setMatrizA(new ArrayList<>(matrizA));
    gauss.setVectorB(new ArrayList<>(vectorB));
    
    gauss = unidadIIIsrv.AlgoritmoGauss(gauss);
    model.addAttribute("gauss", gauss);
    
    return "unit3/gauss/resultado";
}
@Controller
public class GaussJordanController {

    @GetMapping("/unit3/formGaussJordan") 
    public String formGaussJordan(Model model){
    gaussjordan modelgaussjordan = new gaussjordan():
    model.addAttribute("modelgausjordan", modelgaussjordan)
        
    }

   /* @PostMapping("/unit3/gaussjordan/formGaussJordan")
    public String resultadoGaussJordan(@RequestParam int n,
                                       @RequestParam("matrizA") List<Double> matrizA,
                                       @RequestParam("vectorB") List<Double> vectorB,
                                       Model model) {
        GaussJordan gaussJordan = new GaussJordan();
        gaussJordan.setN(n);
        gaussJordan.setMatrizA(new ArrayList<>(matrizA));
        gaussJordan.setVectorB(new ArrayList<>(vectorB));

        gaussJordan = unidadIIIsrv.AlgoritmoGaussJordan(gaussJordan);
        model.addAttribute("gaussjordan", gaussJordan);
        return "unit3/gaussjordan/formgaussjordan";
    }*/
}
}