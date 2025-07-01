package mx.edu.itses.earg.MetodosNumericos.web;

import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.earg.MetodosNumericos.domain.Biseccion;
import mx.edu.itses.earg.MetodosNumericos.domain.NewtonRaphson;
import mx.edu.itses.earg.MetodosNumericos.domain.PuntoFijo;
import mx.edu.itses.earg.MetodosNumericos.domain.ReglaFalsa;
import mx.edu.itses.earg.MetodosNumericos.services.UnidadIIservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class Unit2Controller {

    @Autowired
    private UnidadIIservices bisectionservice;
     @Autowired
    private UnidadIIservices reglafalsaService;
     @Autowired
private UnidadIIservices puntofijoService;
     @Autowired
     private UnidadIIservices newtonraphsonservice;
    @GetMapping("unit2/formBisection")
    public String formBisection(Model model) {
        Biseccion bisection = new Biseccion();
        model.addAttribute("bisection", bisection);
        return "unit2/bisection/formBisection";
    }

    @PostMapping("unit2/solvebisection")
    public String solvebiseccion(Biseccion bisection, Model model) {

        // double valorFX = Funciones.Ecuacion(bisection.getFX(), bisection.getFXL());
        //  log.info("valor de FX:" + valorFX);
        var solveBisection = bisectionservice.AlgoritmoBiseccion(bisection);
        log.info("arreglo" + solveBisection);
        model.addAttribute("solveBisection", solveBisection);
        return "unit2/bisection/solvebisection";
    }
    // REGLA FALSA
  @GetMapping("unit2/formReglaFalsa")
    public String formReglaFalsa(Model model) {

          ReglaFalsa reglafalsa = new ReglaFalsa();

        model.addAttribute("reglafalsa", reglafalsa);
        return "unit2/reglafalsa/formReglaFalsa";
    }
@PostMapping("unit2/solveReglaFalsa")
public String solveReglaFalsa(ReglaFalsa reglafalsa, Model model) {
    var solveReglaFalsa = reglafalsaService.AlgoritmoReglaFalsa(reglafalsa);
    // CAMBIO: usar "solveReglaFalsa" en lugar de "solvereglafalsa"
    model.addAttribute("solveReglaFalsa", solveReglaFalsa);
    return "unit2/reglafalsa/solveReglaFalsa";
}
 @Autowired
    
   @GetMapping("unit2/formPuntoFijo")
    public String formPuntoFijo(Model model) {
        PuntoFijo puntoFijo = new PuntoFijo();  
        model.addAttribute("puntoFijo", puntoFijo);  // atributo para el formulario
        return "unit2/puntofijo/formPuntoFijo";     // nombre plantilla Thymeleaf
    }

    @PostMapping("unit2/solvePuntoFijo")
    public String solvePuntoFijo(@ModelAttribute PuntoFijo puntoFijo, Model model) {
        var solvePuntoFijo = puntofijoService.AlgoritmoPuntoFijo(puntoFijo);
        model.addAttribute("solvePuntoFijo", solvePuntoFijo); // resultados para la tabla
        return "unit2/puntofijo/solvePuntoFijo";             // plantilla resultados
    }
    @GetMapping("unit2/formNewtonRaphson")
    public String formNewtonRaphson(Model model) {

          NewtonRaphson newtonraphson = new NewtonRaphson();

        model.addAttribute("newtonraphson", newtonraphson);
        return "unit2/newtonraphson/formNewtonRaphson";
    }
@PostMapping("unit2/solveNewtonRaphson")
public String solveNewtonRaphson(NewtonRaphson newtonraphson, Model model) {
    var solveNewtonRaphson = newtonraphsonservice.AlgoritmoNewtonRaphson(newtonraphson);
    model.addAttribute("solveNewtonRaphson", solveNewtonRaphson);
    return "unit2/newtonraphson/solveNewtonRaphson";
}
}
