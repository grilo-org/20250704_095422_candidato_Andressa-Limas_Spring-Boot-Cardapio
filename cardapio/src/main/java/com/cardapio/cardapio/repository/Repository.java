package com.cardapio.cardapio.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cardapio.cardapio.model.Food;

@org.springframework.stereotype.Repository 
public interface Repository  extends CrudRepository <Food, Integer>{
    
    List<Food> findAll();

    List<Food> findByNomeContaining(String termo);

    Food findByCodigo(int codigo);

    @Query(value = "SELECT SUM(preco) FROM foods", nativeQuery = true)
    int somaPrecos();

    @Query(value = "SELECT * FROM foods WHERE preco >= :preco", nativeQuery = true)
    List<Food> precoMaiorIgual(int preco);

    int countByCodigo(int codigo);

}
