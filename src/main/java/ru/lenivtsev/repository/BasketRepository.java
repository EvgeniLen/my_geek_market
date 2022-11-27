package ru.lenivtsev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lenivtsev.model.Basket;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    @Query(value = """
            select * from baskets b
            where (b.user_id = :id)
            """,
            nativeQuery = true)
    Optional<Basket> findBasketByOwnerId(Long id);
    //public Basket findBasketByOwner(User owner);


}
