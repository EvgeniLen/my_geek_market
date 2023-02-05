package ru.lenivtsev.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lenivtsev.market.core.service.StatisticService;

@Controller
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping
    public String statisticList(Model model){
        model.addAttribute("stat", statisticService.getStat());
        return "statistic";
    }
}
