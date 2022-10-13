package com.example.retobicicleta.Controller;

import com.example.retobicicleta.Model.Message;
import com.example.retobicicleta.Model.ReportClient;
import com.example.retobicicleta.Model.Reservation;
import com.example.retobicicleta.Model.Status;
import com.example.retobicicleta.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin(origins="*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/Reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<Reservation> getAll(){ return reservationService.getAll();}
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation p){return reservationService.save(p);}
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation){return reservationService.update(reservation);}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){return reservationService.deleteReservation(id);}

    @GetMapping("/report-status")
    public Status getStatus(){
        return reservationService.getStatus();
    }
    @GetMapping("/report-clients")
    public List<ReportClient> getReportClient(){
        return reservationService.getReportClient();
    }

    @GetMapping("/report-dates/{startDate}/{devolutionDate}")
    public List<Reservation> getReportdate(@PathVariable String startDate,@PathVariable String devolutionDate) {
        Date date1= new Date();
        Date date2= new Date();
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            date2=new SimpleDateFormat("yyyy-MM-dd").parse(devolutionDate);
        } catch (ParseException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservationService.getReportDates(date1,date2);
    }






}