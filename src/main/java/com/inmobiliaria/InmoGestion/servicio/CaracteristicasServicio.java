package com.inmobiliaria.InmoGestion.servicio;


import com.inmobiliaria.InmoGestion.modelo.Caracteristicas;
import com.inmobiliaria.InmoGestion.repositorio.CaracteristicasRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CaracteristicasServicio {

    private final CaracteristicasRepositorio caracteristicasRepositorio;

    @Transactional
    public Caracteristicas crear(Caracteristicas dto){
        try{
            return caracteristicasRepositorio.save(dto);
        }catch (Exception e){
            throw new RuntimeException("Error al crear el registro");
        }
    }



    @Transactional
    public Caracteristicas actualizar(Long id, Caracteristicas dto){
        try {
            Caracteristicas c = caracteristicasRepositorio.findById(id).orElse(null);
            if (c != null){
                c.setNombre(dto.getNombre());
                return c;
            }else{
                throw new RuntimeException("No se ha encontrado el registro");
            }

        }catch (Exception e){
            throw new RuntimeException("Error al actualizar la caracteristica");
        }
    }

    @Transactional(readOnly = true)
    public List<Caracteristicas> obtenerCaracteristicas(){
        return caracteristicasRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Caracteristicas> obtenerPorId(Long id){
        return caracteristicasRepositorio.findById(id);
    }

    @Transactional
    public HashMap<String, String> deleteById(Long id){
        try {
            HashMap<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Se ha eliminado correctamente el registro");
            caracteristicasRepositorio.deleteById(id);
            return respuesta;
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el registro");
        }
    }

}
