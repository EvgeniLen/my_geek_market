package ru.lenivtsev.market.orders.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lenivtsev.market.api.dto.OrderStatusDto;
import ru.lenivtsev.market.orders.model.mapper.OrderStatusMapper;
import ru.lenivtsev.market.orders.repositories.OrderStatusRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;
    private final OrderStatusMapper orderStatusMapper;
    public Optional<OrderStatusDto> getOrderStatus(long id) {
        return Optional.ofNullable(orderStatusMapper.map(orderStatusRepository.getOrderStatusById(id)));
    }
}
