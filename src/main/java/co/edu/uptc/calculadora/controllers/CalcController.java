package co.edu.uptc.calculadora.controllers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.calculadora.services.CalcService;
import co.edu.uptc.calculadora.utils.ContainerInfoUtil;

@RestController
@RequestMapping("/api")
public class CalcController {

    private final CalcService calcService;

    private static final Logger logger = LoggerFactory.getLogger(CalcController.class);

    public CalcController(CalcService calcService) {
        this.calcService = calcService;
    }

    @GetMapping("/calcular")
    public Map<String, Object> calcular(@RequestParam double num1, @RequestParam double num2, @RequestParam String operation) {
        logger.info("Recibiendo solicitud de calculo: {} entre {} y {}", operation, num1, num2);
        double result = calcService.calculate(num1, num2, operation);
        logger.info("Solicitud procesada correctamente por el contenedor {} ({})",
                ContainerInfoUtil.getContainerName(), ContainerInfoUtil.getContainerId());
        return Map.of(
                "numero1", num1,
                "numero2", num2,
                "operacion", operation,
                "resultado", result,
                "contenedorId", ContainerInfoUtil.getContainerId(),
                "contenedorNombre", ContainerInfoUtil.getContainerName());
    }

    @GetMapping("/operador")
    public Map<String, Object> operador(
            @RequestParam double numero1,
            @RequestParam double numero2,
            @RequestParam String operacion) {

        logger.info("Endpoint /operador: {} entre {} y {}", operacion, numero1, numero2);

        // Mapear operaciones
        String operation = switch (operacion.toLowerCase()) {
            case "suma" ->
                "add";
            case "resta" ->
                "res";
            case "multiplicacion" ->
                "mul";
            case "division" ->
                "divide";
            default ->
                operacion;
        };

        double result = calcService.calculate(numero1, numero2, operation);
        logger.info("Solicitud /operador procesada por el contenedor {} ({})",
                ContainerInfoUtil.getContainerName(), ContainerInfoUtil.getContainerId());
        return Map.of(
                "numero1", numero1,
                "numero2", numero2,
                "operacion", operacion,
                "resultado", result,
                "contenedorId", ContainerInfoUtil.getContainerId(),
                "contenedorNombre", ContainerInfoUtil.getContainerName());
    }

}
