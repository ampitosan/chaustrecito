package com.example.retobicicleta.Repository.CrudRepository;

import com.example.retobicicleta.Model.Bike;
import org.springframework.data.repository.CrudRepository;

public interface BikeCrudRepository extends CrudRepository<Bike, Integer> {
}
