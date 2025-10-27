package kdg.programming6.keepdishesgoing.restaurant.port.out;

import kdg.programming6.keepdishesgoing.common.vo.Address;
import kdg.programming6.keepdishesgoing.common.vo.Coordinates;

import java.util.Optional;

public interface GeocodingPort {
    Optional<Coordinates> getCoordinates(Address address);
}
