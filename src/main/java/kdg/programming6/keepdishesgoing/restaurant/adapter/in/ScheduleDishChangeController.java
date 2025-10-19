package kdg.programming6.keepdishesgoing.restaurant.adapter.in;

import kdg.programming6.keepdishesgoing.schedule.port.in.ScheduleDishChangeUseCase;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/schedules")
public class ScheduleDishChangeController {

    private final ScheduleDishChangeUseCase scheduleDishChangeUseCase;

    public ScheduleDishChangeController(ScheduleDishChangeUseCase scheduleDishChangeUseCase) {
        this.scheduleDishChangeUseCase = scheduleDishChangeUseCase;
    }

    @PostMapping
    public void scheduleChange(@RequestBody ScheduleRequest request) {
        scheduleDishChangeUseCase.schedule(
                request.dishIds(),
                request.action(),
                request.scheduledTime()
        );
    }

    public record ScheduleRequest(List<UUID> dishIds, String action, LocalDateTime scheduledTime) {}
}
