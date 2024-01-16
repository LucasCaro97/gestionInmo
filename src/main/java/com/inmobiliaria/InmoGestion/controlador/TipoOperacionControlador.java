package com.inmobiliaria.InmoGestion.controlador;

import com.inmobiliaria.InmoGestion.modelo.TipoOperacion;
import com.inmobiliaria.InmoGestion.servicio.TipoOperacionServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipoOperacion")
@RequiredArgsConstructor
public class TipoOperacionControlador {

    private final TipoOperacionServicio tipoOperacionServicio;


    @GetMapping
    public ResponseEntity<List<TipoOperacion>>  obtenerTodas(){
        try{
            List<TipoOperacion> listaTipoOperacion = tipoOperacionServicio.obtenerTodos();
            return ResponseEntity.ok(listaTipoOperacion);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoOperacion> obtenerPorId(@PathVariable Long id){
        try{
            Optional<TipoOperacion> t = tipoOperacionServicio.obtenerPorId(id);
            return t.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<TipoOperacion> crear(@RequestBody TipoOperacion dto){
        try{
            TipoOperacion t = tipoOperacionServicio.crear(dto);
            return new ResponseEntity<>(t, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public  ResponseEntity<TipoOperacion> actualizar (@PathVariable Long id, @RequestBody TipoOperacion dto){
        try{
            TipoOperacion t = tipoOperacionServicio.actualizar(id, dto);
            if(t != null) return new ResponseEntity<>(t, HttpStatus.OK);
            else return ResponseEntity.notFound().build();

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String,String>> eliminar(@PathVariable Long id){
        try{
            HashMap<String, String> respuesta = tipoOperacionServicio.eliminarPorId(id);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




}
