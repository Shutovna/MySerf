package ru.shutovna.moyserf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shutovna.moyserf.service.IStatisticsService;

@RestController
@RequestMapping("/api/stats")
public class StatisticsController {
    @Autowired
    private IStatisticsService statisticsService;

    @GetMapping("/advertisersCount")
    public int getAdvertisersCount() {
        return statisticsService.getAdvertisersCount();
    }

    @GetMapping("/workersCount")
    public int getWorkersCount() {
        return statisticsService.getWorkersCount();
    }

    @GetMapping("/totalIncome")
    public long getTotalIncome() {
        return statisticsService.getTotalIncome();
    }

    @GetMapping("/totalReferalsIncome")
    public long getTotalReferalsIncome() {
        return statisticsService.getTotalReferalsIncome();
    }

    @GetMapping("/userViewCount")
    public int getUserViewCount() {
        return statisticsService.getUserViewCount();
    }

    @GetMapping("/userEarned")
    public long getUserEarned() {
        return statisticsService.getUserEarned();
    }

    @GetMapping("/userEarnedByReferals")
    public long getUserEarnedByReferals() {
        return statisticsService.getUserEarnedByReferals();
    }

    @GetMapping("/myReferalsCount")
    public int getMyReferalsCount() {
        return statisticsService.getMyReferalsCount();
    }

    @GetMapping("/myReferalsIncome")
    public long getMyReferalsIncome() {
        return statisticsService.getMyReferalsIncome();
    }

    @GetMapping("/myReferalsVieewCount")
    public long getMyReferalsViewCount() {
        return statisticsService.getMyReferalsViewCount();
    }
}
