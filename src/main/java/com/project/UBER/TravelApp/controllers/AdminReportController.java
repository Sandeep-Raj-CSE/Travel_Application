package com.project.UBER.TravelApp.controllers;

import com.project.UBER.TravelApp.services.ReportService;
import com.project.UBER.TravelApp.utils.DateRangeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/reports")
public class AdminReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/revenue")
    public Double revenue(@RequestParam(required = false) LocalDate from,
                          @RequestParam(required = false) LocalDate to) {

        LocalDateTime f = DateRangeUtil.startofDay(from);
        LocalDateTime t = DateRangeUtil.endofDay(to);
        return reportService.totalRevenue(f, t);
    }

    @GetMapping("/rides-per-day")
    public Map<String, Long> ridesPerDay(@RequestParam(required = false) LocalDate from,
                                         @RequestParam(required = false) LocalDate to) {

        LocalDateTime f = DateRangeUtil.startofDay(from);
        LocalDateTime t = DateRangeUtil.endofDay(to);
        return reportService.ridesCountPerDay(f, t);
    }

    @GetMapping("/top-drivers")
    public List<Map.Entry<Long, Long>> topDrivers(@RequestParam(defaultValue = "5") int n,
                                                  @RequestParam(required = false) LocalDate from,
                                                  @RequestParam(required = false) LocalDate to) {

        LocalDateTime f = DateRangeUtil.startofDay(from);
        LocalDateTime t = DateRangeUtil.endofDay(to);
        return reportService.topDrivers(n, f, t);
    }
}
