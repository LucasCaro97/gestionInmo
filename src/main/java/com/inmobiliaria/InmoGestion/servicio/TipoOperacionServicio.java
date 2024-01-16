package com.inmobiliaria.InmoGestion.servicio;


import com.inmobiliaria.InmoGestion.modelo.Servicios;
import com.inmobiliaria.InmoGestion.modelo.TipoOperacion;
import com.inmobiliaria.InmoGestion.repositorio.TipoOperacionRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoOperacionServicio {

    public final TipoOperacionRepositorio tipoOperacionRepositorio;

    @Transactional
    public TipoOperacion crear (TipoOperacion dto){
        try{
            return tipoOperacionRepositorio.save(dto);
        }catch (Exception e){
            throw new RuntimeException("Error al crear el registro");
        }
    }


    @Transactional
    public TipoOperacion actualizar (Long id, TipoOperacion dto){
        try{
            TipoOperacion s = tipoOperacionRepositorio.findById(id).orElse(null);
            if (s != null){
                s.setNombre(dto.getNombre());
                return tipoOperacionRepositorio.save(s);
            }else{
                throw new RuntimeException("No se ha encontrado el registro");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el registro");
        }
    }

    @Transactional(readOnly = true)
    public Optional<TipoOperacion> obtenerPorId(Long id){
        return tipoOperacionRepositorio.findById(id);
    }


    @Transactional(readOnly = true)
    public List<TipoOperacion> obtenerTodos(){
        return tipoOperacionRepositorio.findAll();
    }

    @Transactional
    public HashMap<String, String> eliminarPorId(Long id){
        try {
            HashMap<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Se ha eliminardo correctamente el registro");
            tipoOperacionRepositorio.deleteById(id);
            return  respuesta;
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el registro");
        }
    }

}
