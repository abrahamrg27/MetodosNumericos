package mx.edu.itses.earg.metodosnumerico.web;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.earg.metodosnumerico.services.UnidadIIservices;
import mx.edu.itses.earg.metodosnumerico.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/unit2")
public class Unit2Controller {

    @Autowired
    private UnidadIIservices bisectionservice;

    @GetMapping
    public String indexUnit2() {
        return "unit2/index_1";
    }


    @GetMapping("/formBisection")
    public String formBisection(Model model) {
        model.addAttribute("bisection", new Biseccion());
        return "unit2/bisection/formBisection";
    }

    @PostMapping("/solvebisection")
    public String solveBisection(Biseccion bisection, Model model) {
        var solveBisection = bisectionservice.AlgoritmoBiseccion(bisection);
        model.addAttribute("solveBisection", solveBisection);
        return "unit2/bisection/solvebisection";
    }

    @GetMapping("/formReglaFalsa")
    public String formReglaFalsa(Model model) {
        model.addAttribute("reglafalsa", new ReglaFalsa());
        return "unit2/reglafalsa/formReglaFalsa";
    }

    @PostMapping("/solveReglaFalsa")
    public String solveReglaFalsa(ReglaFalsa reglafalsa, Model model) {
        var solveReglaFalsa = bisectionservice.AlgoritmoReglaFalsa(reglafalsa);
        model.addAttribute("solveReglaFalsa", solveReglaFalsa);
        return "unit2/reglafalsa/solveReglaFalsa";
    }

    @GetMapping("/formPuntoFijo")
    public String formPuntoFijo(Model model) {
        model.addAttribute("PuntoFijo", new PuntoFijo());
        return "unit2/PuntoFijo/formPuntoFijo";
    }

    @PostMapping("/solvePuntoFijo")
    public String solvePuntoFijo(PuntoFijo puntoFijo, Model model) {
        var solvePuntoFijo = bisectionservice.AlgoritmoPuntoFijo(puntoFijo);
        model.addAttribute("solvePuntoFijo", solvePuntoFijo);
        return "unit2/PuntoFijo/solvePuntoFijo";
    }

    @GetMapping("/newtonraphson")
    public String formNewtonRaphson(Model model) {
        model.addAttribute("newtonraphson", new NewtonRaphson());
        return "unit2/newtonraphson/formNewtonRaphson";
    }

    @PostMapping("/solveNewtonRaphson")
    public String solveNewtonRaphson(@ModelAttribute NewtonRaphson newtonraphson, Model model) {
        var solveNewtonRaphson = bisectionservice.AlgoritmoNewtonRaphson(newtonraphson);
        model.addAttribute("solveNewtonRaphson", solveNewtonRaphson);
        return "unit2/newtonraphson/solveNewtonRaphson";
    }

    @GetMapping("/formSecante")
    public String formSecante(Model model) {
        model.addAttribute("secante", new Secante());
        return "unit2/secante/formSecante";
    }

    @PostMapping("/solveSecante")
    public String solveSecante(Secante secante, Model model) {
        var solveSecante = bisectionservice.AlgoritmoSecante(secante);
        model.addAttribute("solveSecante", solveSecante);
        return "unit2/secante/solveSecante";
    }

    @GetMapping("/secantemodificada")
    public String formSecanteModificada(Model model) {
        model.addAttribute("secantemodificada", new SecanteModificada());
        return "unit2/secantemodificada/formSecanteModificada";
    }

    @PostMapping("/secantemodificada")
    public String solveSecanteModificada(@ModelAttribute SecanteModificada secanteModificada, Model model) {
        ArrayList<SecanteModificada> resultado = bisectionservice.AlgoritmoSecanteModificada(secanteModificada);
        model.addAttribute("resultado", resultado);
        return "unit2/secantemodificada/solveSecanteModificada";
    }
}
