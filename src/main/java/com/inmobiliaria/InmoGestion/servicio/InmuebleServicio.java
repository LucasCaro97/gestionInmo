package com.inmobiliaria.InmoGestion.servicio;


import com.inmobiliaria.InmoGestion.modelo.Inmueble;
import com.inmobiliaria.InmoGestion.modelo.Servicios;
import com.inmobiliaria.InmoGestion.modelo.TipoOperacion;
import com.inmobiliaria.InmoGestion.repositorio.InmuebleRepositorio;
import com.inmobiliaria.InmoGestion.repositorio.TipoOperacionRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InmuebleServicio {

    public final InmuebleRepositorio inmuebleRepositorio;

    @Transactional
    public Inmueble crear (Inmueble dto){
        try{
            return inmuebleRepositorio.save(dto);
        }catch (Exception e){
            throw new RuntimeException("Error al crear el registro");
        }
    }


    @Transactional
    public Inmueble actualizar (Long id, Inmueble dto){
        try{
            Inmueble i = inmuebleRepositorio.findById(id).orElse(null);
            if (i != null){
                i.setNombre(dto.getNombre());
                i.setProvincia(dto.getProvincia());
                i.setCiudad(dto.getCiudad());
                i.setCategoria(dto.getCategoria());
                i.setAmbientes(dto.getAmbientes());
                i.setServicios(dto.getServicios());
                i.setCaracteristicas(dto.getCaracteristicas());
                i.setDescripcion(dto.getDescripcion());
                i.setListaImagenes(dto.getListaImagenes());
                i.setDireccion(dto.getDireccion());
                i.setPrecio(dto.getPrecio());
                i.setTipoOperacion(dto.getTipoOperacion());
                return inmuebleRepositorio.save(i);
            }else{
                throw new RuntimeException("No se ha encontrado el registro");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el registro");
        }
    }

    @Transactional(readOnly = true)
    public Optional<Inmueble> obtenerPorId(Long id){
        return inmuebleRepositorio.findById(id);
    }


    @Transactional(readOnly = true)
    public List<Inmueble> obtenerTodos(){
        return inmuebleRepositorio.findAll();
    }

    @Transactional
    public HashMap<String, String> eliminarPorId(Long id){
        try {
            HashMap<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Se ha eliminardo correctamente el registro");
            inmuebleRepositorio.deleteById(id);
            return  respuesta;
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el registro");
        }
    }


}
