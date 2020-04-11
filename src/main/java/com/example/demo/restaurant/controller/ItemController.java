package com.example.demo.restaurant.controller;

import com.example.demo.restaurant.model.Item;
import com.example.demo.restaurant.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemRepository repository;

    @GetMapping("/items")
    public List<Item> getItems() {
        return repository.findAll();
    }

    @PostMapping("/items")
    public Item createItem(@RequestBody Item item) {
        return repository.save(item);
    }

    @PutMapping("/items/{id}")
    public Item updateItem(@RequestBody Item item) {
        return repository.save(item);
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable long id, HttpServletResponse response) {
        try {
            repository.deleteById(id);
        }catch (Exception e){
           response.setStatus(500);
        }

    }
}
