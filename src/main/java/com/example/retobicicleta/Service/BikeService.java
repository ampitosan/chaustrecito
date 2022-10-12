package com.example.retobicicleta.Service;

import com.example.retobicicleta.Model.Admin;
import com.example.retobicicleta.Model.Bike;
import com.example.retobicicleta.Repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeService {
    @Autowired
    private BikeRepository bikeRepository;

    public List<Bike> getAll(){
        return bikeRepository.getAll();
    }

    public Optional<Bike> getBike(int id){
        return bikeRepository.getBike(id);
    }

    public Bike save (Bike bike){
        if (bike.getId() == null){
            return bikeRepository.save(bike);
        } else {
            Optional<Bike> bike1 = getBike(bike.getId());
            if(bike1.isEmpty()){
                return bikeRepository.save(bike);
            } else {
                return bike;
            }
        }
    }

    public  Bike update(Bike bike){
        if(bike.getId()!=null){
            Optional<Bike> bike1= getBike(bike.getId());
            if(!bike1.isEmpty()){
                if(bike.getName()!=null){
                    bike1.get().setName((bike.getName()));

                }
                if(bike.getBrand()!=null){
                    bike1.get().setBrand(bike.getBrand());

                }
                if(bike.getYear()!=null){
                    bike1.get().setYear(bike.getYear());
                }
                if(bike.getDescription()!=null){
                    bike1.get().setDescription(bike.getDescription());
                }
                if(bike.getCategory()!=null){
                    bike1.get().setCategory(bike.getCategory());
                }
                return bikeRepository.save(bike1.get());
            }

        }
        return bike;
    }
    public boolean deleteBike(int id){
        boolean resultado = getBike(id).map(bikeporeliminar ->{
            bikeRepository.delete(bikeporeliminar);
            return true;
        }).orElse(false);
        return resultado;
    }
    public Optional<Bike> getBikeId(int id){
        return bikeRepository.getBike(id);}
}
