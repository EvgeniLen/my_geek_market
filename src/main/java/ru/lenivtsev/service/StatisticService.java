package ru.lenivtsev.service;

import org.springframework.stereotype.Service;
import ru.lenivtsev.configAspect.Statistic;

import java.util.List;

@Service
public class StatisticService {
    private Statistic statistic;

    public void addStatistic(String name, Long time) {
        if (this.statistic == null) {
            this.statistic = new Statistic();
        }
        statistic.addStat(name, time);
    }

    public List<String> getStat(){
        return statistic.getStat();
    }


}
