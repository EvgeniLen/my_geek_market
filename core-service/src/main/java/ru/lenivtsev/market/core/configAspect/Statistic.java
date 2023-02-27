package ru.lenivtsev.market.core.configAspect;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Getter
public class Statistic {
    private Map<String, Long> statisticMap;

    public Statistic() {
        this.statisticMap = new ConcurrentHashMap<>();
    }

    public void addStat(String name, Long time){
        long oldTime = statisticMap.getOrDefault(name, 0L);
        statisticMap.put(name, oldTime + time);
    }

    public List<String> getStat(){
        List<String> list = new ArrayList<>();
        statisticMap.forEach((s, aLong) -> {
            list.add(s + "##" + aLong);
        });
        return list;
    }
}
