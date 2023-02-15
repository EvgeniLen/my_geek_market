package ru.lenivtsev.market.baskets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lenivtsev.market.baskets.model.Basket;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    @Query(value = """
            delete from baskets b
            where b.user_id = :id""",
        nativeQuery = true)
    void deleteBasketByUser(long id);

    @Query(value = """
            select * from baskets b
            where (b.user_id = :id)
            """,
            nativeQuery = true)
    Optional<Basket> findBasketByOwnerId(Long id);

}
