package ru.lenivtsev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lenivtsev.model.BasketItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {

    @Query(value = """
            select * from basket_items i
            where (i.product_id = :productId)
            and (i.basket_id = :basketId)
            """,
            nativeQuery = true)
    Optional<BasketItem> findBasketItemByProductIdAndBasketId(Long productId, Long basketId);

    List<BasketItem> findBasketItemsByBasketId(Long id);
}
