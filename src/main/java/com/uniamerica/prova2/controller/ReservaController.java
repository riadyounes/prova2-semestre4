package com.uniamerica.prova2.controller;

import com.uniamerica.prova2.model.Reserva;
import com.uniamerica.prova2.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<Reserva> store(@RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.store(reserva));
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> index(){
        return ResponseEntity.ok(reservaService.index());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Reserva>> show(@PathVariable Long id){
        return ResponseEntity.ok(reservaService.show(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Reserva>> searchByDate(
            @RequestParam("data_retirada") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data_retirada ,
            @RequestParam("data_devolucao") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data_devolucao){
        return ResponseEntity.ok(reservaService.searchByDate(data_retirada, data_devolucao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> update(@PathVariable Long id,@RequestBody Reserva reserva){
        Optional<Reserva> optionalReserva = reservaService.show(id);

        if (optionalReserva.isPresent()){
            return ResponseEntity.ok(reservaService.update(reserva));
        }else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reserva> destroy(@PathVariable Long id){
        reservaService.destroy(id);
        return ResponseEntity.ok(null);
    }

}
