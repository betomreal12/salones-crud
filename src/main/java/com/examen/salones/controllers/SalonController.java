package com.examen.salones.controllers;

import com.examen.salones.Salon;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/salones")
public class SalonController {

    List<Salon> lista = new ArrayList<>();

    public SalonController() {

        lista.add(new Salon(1,"A101",40,"Edificio A"));
        lista.add(new Salon(2,"B201",30,"Edificio B"));
        lista.add(new Salon(3,"C301",50,"Edificio C"));
    }

    @GetMapping
    public String listar(Model model){

        model.addAttribute("salones", lista);

        return "lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){

        model.addAttribute("salon", new Salon());

        return "crear";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Salon salon){

        lista.add(salon);

        return "redirect:/salones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model){

        for(Salon s : lista){
            if(s.getId()==id){
                model.addAttribute("salon", s);
            }
        }

        return "editar";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Salon salon){

        for(Salon s : lista){
            if(s.getId()==salon.getId()){
                s.setNombre(salon.getNombre());
                s.setCapacidad(salon.getCapacidad());
                s.setEdificio(salon.getEdificio());
            }
        }

        return "redirect:/salones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id){

        lista.removeIf(s -> s.getId()==id);

        return "redirect:/salones";
    }
}