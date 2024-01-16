package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.Categoria;
import com.inmobiliaria.InmoGestion.repositorio.CategoriaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaServicio {

    private final CategoriaRepositorio categoriaRepositorio;


    @Transactional
    public Categoria crear(Categoria dto){
        try {
            return categoriaRepositorio.save(dto);
        }catch (Exception e){
            throw new RuntimeException("Error al crear el registro");
        }
    }

    @Transactional
    public Categoria actualizar (Long id, Categoria dto){
        try {
            Categoria c = categoriaRepositorio.findById(id).orElse(null);
            if(c != null){
                c.setNombre(dto.getNombre());
                return categoriaRepositorio.save(c);
            }else{
                throw new RuntimeException("No se ha encontrado el registro");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar la categoria");
        }
    }

    @Transactional(readOnly = true)
    public Optional<Categoria> obtenerPorId(Long id){
        return categoriaRepositorio.findById(id);
    }


    @Transactional(readOnly = true)
    public List<Categoria> obtenerTodas(){
        return categoriaRepositorio.findAll();
    }

    public HashMap<String, String> eliminarPorId(Long id){
        try{
            HashMap<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Se ha eliminado correctamente el registro");
            categoriaRepositorio.deleteById(id);
            return respuesta;
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el registro");
        }
    }

}
