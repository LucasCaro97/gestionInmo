package com.inmobiliaria.InmoGestion.controlador;


import com.inmobiliaria.InmoGestion.modelo.Inmueble;
import com.inmobiliaria.InmoGestion.modelo.Servicios;
import com.inmobiliaria.InmoGestion.servicio.InmuebleServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inmueble")
@RequiredArgsConstructor
public class InmuebleControlador {

    private final InmuebleServicio inmuebleServicio;

    @GetMapping
    public ResponseEntity<List<Inmueble>> obtenerTodas(){
        try{
            List<Inmueble> c = inmuebleServicio.obtenerTodos();
            return ResponseEntity.ok(c);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Inmueble> obtenerPorId(@PathVariable Long id){
        try{
            Optional<Inmueble> c = inmuebleServicio.obtenerPorId(id);
            return c.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
        }catch (Exception e ){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Inmueble> crear(@RequestBody Inmueble dto){
        try{
            Inmueble c = inmuebleServicio.crear(dto);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inmueble> actualizar(@PathVariable Long id , @RequestBody Inmueble dto){
        try{
            Inmueble c = inmuebleServicio.actualizar(id, dto);
            if(c != null) return new ResponseEntity<>(c, HttpStatus.OK);
            else return ResponseEntity.notFound().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> eliminar(@PathVariable Long id){
        try {
            HashMap<String, String> respuesta = inmuebleServicio.eliminarPorId(id);
            return new ResponseEntity<>(respuesta,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
