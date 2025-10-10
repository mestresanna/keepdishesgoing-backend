package kdg.programming6.keepdishesgoing.restaurant.adapter.out;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "dishes")
public class DishJpaEntity {
    @Id
    private UUID id;

    private String name;
    private String description;
    private String pictureUrl;
    private String type;
    private BigDecimal price;
    private String state;
    private boolean hasPendingChanges;
    private LocalDateTime scheduledPublishTime;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private MenuJpaEntity menu;
}
