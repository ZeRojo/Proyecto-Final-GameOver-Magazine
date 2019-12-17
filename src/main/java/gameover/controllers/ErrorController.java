package gameover.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException(NoHandlerFoundException ex, Model modelo) {
    	modelo.addAttribute("errorImg","404");
    	modelo.addAttribute("errorMsg1","NO ENCONTRADA");
    	modelo.addAttribute("errorMsg2","La página que estás buscando no ha sido encontrada.");
        return "error";
    }
    
    
    
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerException(NullPointerException ex, Model modelo) {
    	modelo.addAttribute("errorImg","404");
    	modelo.addAttribute("errorMsg1","NO ENCONTRADA");
    	modelo.addAttribute("errorMsg2","La página que estás buscando no ha sido encontrada.");
    	return "error";
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentException(IllegalArgumentException ex, Model modelo) {
    	modelo.addAttribute("errorImg","505");
    	modelo.addAttribute("errorMsg1","ERROR INTERNO DEL SISTEMA");
    	modelo.addAttribute("errorMsg2","Lo que has hecho ha dado algún problema. Cada vez que lo haces muere un gatico.");
    	return "error";
    }
    
    @ExceptionHandler(InternalServerError.class)
    public String internalServerErrorException(InternalServerError ex, Model modelo) {
    	modelo.addAttribute("errorImg","505");
    	modelo.addAttribute("errorMsg1","ERROR INTERNO DEL SISTEMA");
    	modelo.addAttribute("errorMsg2","Lo que has hecho ha dado algún problema. Cada vez que lo haces muere un gatico.");
    	return "error";
    }
    
    @ExceptionHandler(ClassNotFoundException.class)
    public String classNotFoundException(ClassNotFoundException ex, Model modelo) {
    	modelo.addAttribute("errorImg","505");
    	modelo.addAttribute("errorMsg1","ERROR INTERNO DEL SISTEMA");
    	modelo.addAttribute("errorMsg2","Ha habido un problema al cursar tu petición. No lo vuelvas a hacer plz.");
    	return "error";
    }
}