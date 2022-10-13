package com.example.retobicicleta.Service;

import com.example.retobicicleta.Model.*;
import com.example.retobicicleta.Repository.AdminRepository;
import com.example.retobicicleta.Repository.ClientRepository;
import com.example.retobicicleta.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ClientRepository clientRepository;


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
    public Status getStatus() {
        Status status = new Status();
        List<Reservation> reservations=reservationRepository.getAll();
        int contF=0;
        int contC=0;
        for(Reservation res:reservations){
            if(res.getStatus().equals("completed")){
                contF=contF+1;
            }else if(res.getStatus().equals("cancelled")){
                contC=contC+1;
            }
        }
        status.setCompleted(contF);
        status.setCancelled(contC);
        return status;
    }
    public List<ReportClient> getReportClient() {
        List<ReportClient> repoclient=new ArrayList<ReportClient>();
        List<Client> clients=clientRepository.getAll();
        int total=0;
        for(Client cli:clients){
            for(Reservation res:cli.getReservations()){
                total=total+1;
            }
            ReportClient reportclient= new ReportClient();
            reportclient.setTotal(total);
            reportclient.setClient(cli);
            repoclient.add(reportclient);
            total=0;
        }
        return repoclient;
    }
    public List<Reservation> getReportDates(Date date1, Date date2) {
        List<Reservation> reservations= reservationRepository.getAll();
        List<Reservation> reservationsDate =new ArrayList<Reservation>();
        for(Reservation res:reservations){
            if(date1.compareTo(res.getStartDate()) * res.getStartDate().compareTo(date2) >= 0){
                reservationsDate.add(res);
            }
        }
        return reservationsDate;
    }



}
