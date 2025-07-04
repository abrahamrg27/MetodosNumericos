package mx.edu.itses.earg.metodosnumerico.web;

import lombok.extern.slf4j.Slf4j;
import mx.edu.itses.earg.metodosnumerico.domain.Biseccion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller //importar
@Slf4j
public class MainController {
    @GetMapping("/") // Importar
    public String Inicio(Model model){
        int i = 1;
        log.info("mesnaje de salida: {}", i);
        model.addAttribute("valor", i);
        return "Index";
        
    }
}
