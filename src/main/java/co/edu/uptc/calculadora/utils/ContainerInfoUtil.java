package co.edu.uptc.calculadora.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
    Identificar el contenedor Docker que atendio la solicitud
*/
public class ContainerInfoUtil {

    private ContainerInfoUtil() {
        // Private constructor to prevent instantiation
    }

    public static String getContainerId() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "id-desconocido";
        }
    }

    public static String getContainerName() {
        String name = System.getenv("CONTAINER_NAME");
        return (name != null && !name.isBlank()) ? name : "nombre-desconocido";
    }
}
