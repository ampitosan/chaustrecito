package com.example.retobicicleta.Service;


import com.example.retobicicleta.Model.Message;
import com.example.retobicicleta.Model.Reservation;
import com.example.retobicicleta.Model.Score;
import com.example.retobicicleta.Repository.ReservationRepository;
import com.example.retobicicleta.Repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    private ScoreRepository scoreRepository;

    public List<Score> getAll() {
        return scoreRepository.getAll();
    }

    public Optional<Score> getScore(int id) {
        return scoreRepository.getScore(id);
    }

    public Score save(Score score) {
        if (score.getIdScore() == null) {
            return scoreRepository.save(score);
        } else {
            Optional<Score> score1 = getScore(score.getIdScore());
            if (score1.isEmpty()) {
                return scoreRepository.save(score);
            } else {
                return score;
            }
        }
    }
    public Score update(Score score){
        if(score.getIdScore()!=null){
            Optional<Score> scoreEncontrado = getScore(score.getIdScore());
            if (!scoreEncontrado.isEmpty()){
                if(score.getMessageText()!=null){
                    scoreEncontrado.get().setMessageText(score.getMessageText());

                }
                if(score.getStars()!=null){
                    scoreEncontrado.get().setStars(score.getStars());

                }
                return scoreRepository.save(scoreEncontrado.get());
            }

        }
        return score;
    }
    public boolean deleteScore(int id){
        boolean resultado = getScore(id).map(scoreporeliminar ->{
            scoreRepository.delete(scoreporeliminar);
            return true;
        }).orElse(false);
        return resultado;
    }
    public Optional<Score> getScoreId(int id){
        return scoreRepository.getScore(id);}
}
