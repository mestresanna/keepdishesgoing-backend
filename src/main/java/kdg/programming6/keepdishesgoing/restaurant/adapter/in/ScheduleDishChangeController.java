package kdg.programming6.keepdishesgoing.restaurant.adapter.in;

import kdg.programming6.keepdishesgoing.restaurant.adapter.in.request.ScheduleRequest;
import kdg.programming6.keepdishesgoing.restaurant.port.in.dish.ScheduleDishChangeCommand;
import kdg.programming6.keepdishesgoing.restaurant.port.in.dish.ScheduleDishChangeUseCase;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleDishChangeController {

    private final ScheduleDishChangeUseCase scheduleDishChangeUseCase;

    public ScheduleDishChangeController(ScheduleDishChangeUseCase scheduleDishChangeUseCase) {
        this.scheduleDishChangeUseCase = scheduleDishChangeUseCase;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('owner')")
    public void scheduleChange(@RequestBody ScheduleRequest request) {
        scheduleDishChangeUseCase.scheduleDishChange( new ScheduleDishChangeCommand(
                request.dishIds(),
                request.action(),
                request.scheduledTime()));
    }
}
