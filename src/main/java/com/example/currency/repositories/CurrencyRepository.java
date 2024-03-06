package com.example.currency.repositories;

import com.example.currency.etities.CurrencyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends CrudRepository<CurrencyEntity, Long> {
    List<CurrencyEntity> findByDate(String currencyDate);

    List<CurrencyEntity>  findByCurrency(String currencyCode);
}
