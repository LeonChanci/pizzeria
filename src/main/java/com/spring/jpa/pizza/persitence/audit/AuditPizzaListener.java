package com.spring.jpa.pizza.persitence.audit;

import com.spring.jpa.pizza.persitence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;

public class AuditPizzaListener {
    private PizzaEntity currentValue;
    //Se agregan los métodos publicos y sin retorno

    //Se agrega la anotación @PostLoad para auditar despues de que se haga un select a un objeto Pizza
    @PostLoad
    public void postLoad(PizzaEntity entity) {
        System.out.println("POST LOAD");
        this.currentValue = SerializationUtils.clone(entity);
    }

    //Se agrega la anotación PostPersist para auditar después de que se haga un insert (create)
    //Se pone la anotación @PostUpdate para auditar después de que se haga un update a un objeto Pizza
    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity entity) {
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE: " + this.currentValue);
        System.out.println("NEW VALUE: " + entity.toString());
    }

    //Auditar justo antes de eliminar objeto Pizza -> @PreRemove (Before to Delete Pizza)
    @PreRemove
    public void onPreDelete(PizzaEntity entity) {
        System.out.println(entity.toString());
    }
}
