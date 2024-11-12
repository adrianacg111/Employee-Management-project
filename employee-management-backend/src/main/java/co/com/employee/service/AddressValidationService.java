package co.com.employee.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class AddressValidationService {

    // Expresión regular ajustada
    private static final String ADDRESS_PATTERN = "^(Calle|Carrera|Diagonal|Transversal)\\s\\d{1,3}(\\s[a-zA-Z0-9áéíóúÁÉÍÓÚüÜñÑ]+)*\\s?-?\\d{2}-\\d{2}(\\s[a-zA-Z0-9áéíóúÁÉÍÓÚüÜñÑ]+)?$";

    // Método que valida si la dirección cumple con el patrón
    public boolean isValidAddress(String address) {
        Pattern pattern = Pattern.compile(ADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();  // Retorna true si la dirección es válida, false en caso contrario
    }
}
