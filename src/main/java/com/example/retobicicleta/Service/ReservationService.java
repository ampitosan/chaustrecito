package com.example.retobicicleta.Service;

import com.example.retobicicleta.Model.Admin;
import com.example.retobicicleta.Model.Message;
import com.example.retobicicleta.Model.Reservation;
import com.example.retobicicleta.Repository.AdminRepository;
import com.example.retobicicleta.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> reservation1 = getReservation(reservation.getIdReservation());
            if (reservation1.isEmpty()) {
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> reservatioencontrada = getReservation((reservation.getIdReservation()));
            if (!reservatioencontrada.isEmpty()) {
                if (reservation.getStartDate() != null) {
                    reservatioencontrada.get().setStartDate((reservation.getStartDate()));
                }
                if (reservation.getDevolutionDate() != null) {
                    reservatioencontrada.get().setDevolutionDate(reservation.getDevolutionDate());

                }
                if (reservation.getStatus() != null) {
                    reservatioencontrada.get().setStatus(reservation.getStatus());

                }
                return reservationRepository.save(reservatioencontrada.get());
            }
        }
        return reservation;

    }
    public boolean deleteReservation(int id){
        boolean resultado = getReservation(id).map(reservationporeliminar ->{
            reservationRepository.delete(reservationporeliminar);
            return true;
        }).orElse(false);
        return resultado;
    }
    public Optional<Reservation> getReservationId(int id){
        return reservationRepository.getReservation(id);}
}
