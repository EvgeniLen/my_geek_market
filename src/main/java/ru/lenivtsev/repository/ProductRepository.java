package ru.lenivtsev.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lenivtsev.model.Product;

import java.math.BigDecimal;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = """
            select * from products p 
            where (:productTitleFilter is null or p.title like :productTitleFilter)
            and ((:priceFilterMin is null or :priceFilterMax is null) or p.price between :priceFilterMin and :priceFilterMax)
            and (:priceFilterMax is null or p.price < :priceFilterMax)
            and (:priceFilterMin is null or p.price > :priceFilterMin)
            """,
            countQuery = """
            select count(*) from products p 
            where (:productTitleFilter is null or p.title like :productTitleFilter)
            and ((:priceFilterMin is null or :priceFilterMax is null) or p.price between :priceFilterMin and :priceFilterMax)
            and (:priceFilterMax is null or p.price < :priceFilterMax)
            and (:priceFilterMin is null or p.price > :priceFilterMin)
            """ ,
            nativeQuery = true)
    Page<Product> productByFilter(String productTitleFilter, BigDecimal priceFilterMin, BigDecimal priceFilterMax, Pageable pageable);

}