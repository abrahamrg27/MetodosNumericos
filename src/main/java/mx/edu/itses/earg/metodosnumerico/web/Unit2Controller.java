package mx.edu.itses.earg.metodosnumerico.web;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.earg.metodosnumerico.services.UnidadIIservices;
import mx.edu.itses.earg.metodosnumerico.domain.Biseccion;
import mx.edu.itses.earg.metodosnumerico.domain.NewtonRaphson;
import mx.edu.itses.earg.metodosnumerico.domain.PuntoFijo;
import mx.edu.itses.earg.metodosnumerico.domain.ReglaFalsa;
import mx.edu.itses.earg.metodosnumerico.domain.Secante;
import mx.edu.itses.earg.metodosnumerico.domain.SecanteModificada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class Unit2Controller {

    @Autowired
    private UnidadIIservices bisectionservice;

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

    @GetMapping("unit2/formReglaFalsa")
    public String formReglaFalsa(Model model) {

        ReglaFalsa reglafalsa = new ReglaFalsa();

        model.addAttribute("reglafalsa", reglafalsa);
        return "unit2/reglafalsa/formReglaFalsa";
    }

    @PostMapping("unit2/solveReglaFalsa")
    public String solveReglaFalsa(ReglaFalsa reglafalsa, Model model) {
        var solveReglaFalsa = bisectionservice.AlgoritmoReglaFalsa(reglafalsa);
        model.addAttribute("solveReglaFalsa", solveReglaFalsa);
        return "unit2/reglafalsa/solveReglaFalsa";
    }
    @GetMapping("unit2/formPuntoFijo")
    public String formPuntoFijo(Model model) {
        PuntoFijo puntoFijo = new PuntoFijo();
        model.addAttribute("PuntoFijo", puntoFijo);
        return "unit2/PuntoFijo/formPuntoFijo";
    }

    @PostMapping("unit2/solvePuntoFijo")
    public String solvePuntoFijo(PuntoFijo puntoFijo, Model model) {
        var solvePuntoFijo = bisectionservice.AlgoritmoPuntoFijo(puntoFijo);
        model.addAttribute("solvePuntoFijo", solvePuntoFijo);
        return "unit2/PuntoFijo/solvePuntoFijo";
    }
@GetMapping("unit2/newtonraphson")
public String formNewtonRaphson(Model model) {
    NewtonRaphson newtonraphson = new NewtonRaphson();
    model.addAttribute("newtonraphson", newtonraphson);
    return "unit2/newtonraphson/formNewtonRaphson";
}
    @PostMapping("/unit2/solveNewtonRaphson")
    public String solveNewtonRaphson(@ModelAttribute NewtonRaphson newtonraphson, Model model) {
        var solveNewtonRaphson = bisectionservice.AlgoritmoNewtonRaphson(newtonraphson);
        model.addAttribute("solveNewtonRaphson", solveNewtonRaphson);
        return "unit2/newtonraphson/solveNewtonRaphson";
    }
    @GetMapping("unit2/formSecante")
public String formSecante(Model model) {
    model.addAttribute("secante", new Secante());
    return "unit2/secante/formSecante";
}

@PostMapping("unit2/solveSecante")
public String solveSecante(Secante secante, Model model) {
    var solveSecante = bisectionservice.AlgoritmoSecante(secante);
    model.addAttribute("solveSecante", solveSecante);
    return "unit2/secante/solveSecante";
}
@GetMapping("/unit2/secantemodificada")
public String formSecanteModificada(Model model) {
    model.addAttribute("secantemodificada", new SecanteModificada());
    return "unit2/secantemodificada/formSecanteModificada";
}

@PostMapping("/unit2/secantemodificada")
public String solveSecanteModificada(@ModelAttribute SecanteModificada secanteModificada, Model model) {
    ArrayList<SecanteModificada> resultado = bisectionservice.AlgoritmoSecanteModificada(secanteModificada);
    model.addAttribute("resultado", resultado);
    return "unit2/secantemodificada/formSecanteModificada";
}
}