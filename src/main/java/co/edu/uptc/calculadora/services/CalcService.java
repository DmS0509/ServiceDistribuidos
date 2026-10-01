package co.edu.uptc.calculadora.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CalcService {

    private final Logger logger = LoggerFactory.getLogger(CalcService.class);

    public double calculate(double num1, double num2, String operation) {

        logger.info("Calculando: {} entre {} y {}", operation, num1, num2);

        double result;

        switch (operation.toLowerCase()) {
            case "add" -> {
                result = num1 + num2;
                break;
            }
            case "res" -> {
                result = num1 - num2;
                break;
            }
            case "mul" -> {
                result = num1 * num2;
                break;
            }
            case "divide" -> {
                if (num2 == 0) {
                    logger.error("intento de division por cero");
                    throw new IllegalArgumentException("No se puede dividir entre cero");
                }
                result = num1 / num2;
                break;
            }
            default -> {
                logger.error("Operacion no valida: {}", operation);
                throw new IllegalArgumentException("Operacion no valida: " + operation);
            }
        }
        logger.info("El resultado de la operacion es: {}", result);
        return result;
    }

}
