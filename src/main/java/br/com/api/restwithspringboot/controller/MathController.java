package br.com.api.restwithspringboot.controller;

import br.com.api.restwithspringboot.exception.UnsuportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {
    public static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsuportedMathOperationException("Please, insert a number");
        }
        Double sum = convertToDouble(numberOne) + convertToDouble(numberTwo);
            return sum;
    }

     private Double convertToDouble(String strNumber) {
         if(strNumber == null) return 0D;
         String number = strNumber.replaceAll(",", ".");
         if(isNumeric(number)) return Double.parseDouble(number);
         return 1D;
     }

     private boolean isNumeric(String strNumber) {
        if(strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
     }
}
