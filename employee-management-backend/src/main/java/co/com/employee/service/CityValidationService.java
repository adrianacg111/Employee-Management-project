package co.com.employee.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CityValidationService {

    @Value("${opencage.api.key}")  // La clave API en application.properties
    private String apiKey;

    private static final String API_URL = "https://api.opencagedata.com/geocode/v1/json?q={city}&key={apiKey}";

    private final RestTemplate restTemplate;

    public CityValidationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isValidCity(String city) {
        String url = API_URL;
        try {
            // Realizar la solicitud HTTP GET y obtener la respuesta JSON como un JsonNode
            String response = restTemplate.getForObject(url, String.class, city, apiKey);
            if (response != null) {
                // Parsear la respuesta JSON
                JsonNode jsonResponse = new ObjectMapper().readTree(response);
                JsonNode results = jsonResponse.get("results");

                // Verificar si hay resultados y si contienen datos geográficos válidos
                return results != null && results.size() > 0 && results.get(0).has("geometry");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;  // Si no se obtiene una respuesta válida o si la respuesta no contiene los datos esperados
    }
}
