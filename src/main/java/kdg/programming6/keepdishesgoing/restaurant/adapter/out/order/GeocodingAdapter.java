package kdg.programming6.keepdishesgoing.restaurant.adapter.out.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import kdg.programming6.keepdishesgoing.common.vo.Address;
import kdg.programming6.keepdishesgoing.common.vo.Coordinates;
import kdg.programming6.keepdishesgoing.restaurant.port.out.GeocodingPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
import java.util.Optional;

@Component
public class GeocodingAdapter implements GeocodingPort {
    private final Logger log = LoggerFactory.getLogger(GeocodingAdapter.class);
    private static final String NOMINATIM_URL = "https://nominatim.openstreetmap.org/search";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Optional<Coordinates> getCoordinates(Address address) {
        try {
            // Validate address fields
            Objects.requireNonNull(address.street(), "Street is null");
            Objects.requireNonNull(address.city(), "City is null");
            Objects.requireNonNull(address.postalCode(), "Postal code is null");
            Objects.requireNonNull(address.country(), "Country is null");

            String query = String.format("%s %d, %s %s, %s",
                    address.street(), address.number(), address.postalCode(), address.city(), address.country());

            String url = UriComponentsBuilder.fromHttpUrl(NOMINATIM_URL)
                    .queryParam("q", query)
                    .queryParam("format", "json")
                    .queryParam("limit", 1)
                    .build()
                    .toUriString();

            log.info("Geocoding query: {}", query);
            log.info("Geocoding URL: {}", url);

            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "KeepDishesGoingApp/1.0");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<NominatimResponse[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    NominatimResponse[].class
            );

            NominatimResponse[] body = response.getBody();
            log.info("Geocoding response: {}", (body != null) ? body.length + " result(s)" : "null response");

            if (body != null && body.length > 0) {
                double lat = Double.parseDouble(body[0].lat);
                double lon = Double.parseDouble(body[0].lon);
                log.info("Parsed coordinates: lat={}, lon={}", lat, lon);
                return Optional.of(new Coordinates(lat, lon));
            } else {
                log.warn("No geocoding result found for query: {}", query);
            }
        } catch (Exception e) {
            log.error("Error during geocoding for address {}: {}", address, e.getMessage(), e);
        }

        return Optional.empty();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class NominatimResponse {
        @JsonProperty("lat")
        String lat;
        @JsonProperty("lon")
        String lon;

        @Override
        public String toString() {
            return "lat=" + lat + ", lon=" + lon;
        }
    }
}
