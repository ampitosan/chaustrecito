package com.example.retobicicleta.Repository.CrudRepository;

import com.example.retobicicleta.Model.Client;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.data.repository.CrudRepository;

public interface ClientCrudRepository extends CrudRepository<Client, Integer> {
}
