package com.spring.jpa.pizza.service;

import com.spring.jpa.pizza.persitence.entity.PizzaEntity;
import com.spring.jpa.pizza.persitence.repository.PizzaPagSortRepository;
import com.spring.jpa.pizza.persitence.repository.PizzaRepository;
import com.spring.jpa.pizza.service.dto.UpdatePizzaPriceDTO;
import com.spring.jpa.pizza.service.exception.EmailApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    //Como esta como final se agrega como contructor de parámetros
    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository){
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    //--------------GET-------------------//

    /**
     * Get all pizzas of pizzaRepository
     */
    public List<PizzaEntity> findAll(){
        //Llamar al jdbcTemplate -> permite crear consultas SQL desde java y convertir
        //El resultado en clases Java
        return this.pizzaRepository.findAll();
    }

    /**
     * Get all pizzas of pizzaPagSortRepository
     */
    public Page<PizzaEntity> findAll(int page, int elements){
        //Paginar los resultados
        PageRequest pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    public PizzaEntity get(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    /**
     * Get all pizzas available of pizzaRepository
     */
    public List<PizzaEntity> getAvailable(){
        return this.pizzaRepository.findByAvailableTrueOrderByPrice();
    }

    /**
     * Get all pizzas available of pizzaPagSortRepository
     */
    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection){
        //sortBy -> Campo por el que se ordenara la consulta
        //sortDirection -> Forma de ordenación DESC o ASC
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        PageRequest pageRequest = PageRequest.of(page, elements, sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public PizzaEntity getByName(String name){
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("The pizza not exist"));
    }

    public List<PizzaEntity> getWith(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getCheapest(double price){
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceDesc(price);
    }

    public int getCountPizzasVegan(){
        return this.pizzaRepository.countByVeganTrue();
    }


    //--------------POST-------------------//
    public PizzaEntity save(PizzaEntity pizzaEntity){
        return pizzaRepository.save(pizzaEntity);
    }

    //--------------PUT-------------------//
    //Se usa @Transactional para hacer inserciones, modificaciones y eliminaciones teniendo en cuenta
    //las propiedades ACID (Atomicidad, Consistencia, Aislamiento y Durabilidad)
    //Garantiza cada una de los llamados a la BD y que todos se realicen bien, sino se hace rollback

    //Atributo noRollbackFor, para que NO se haga un rollback cuando suceda una excepcion de tipo EmailApiException.class
    //Si sucede una exception de otro tipo, SI se hará rollback

    //Atributo propagation, por defecto es Propagation.REQUIRED (Obliga a tener una transacción, si no exite la crea)
    //Propagation.MANDATORY -> no es necesario que exista una transacción pero retorna exception para crearla
    @Transactional(noRollbackFor = EmailApiException.class, propagation = Propagation.MANDATORY)
    public void updatePrice(UpdatePizzaPriceDTO dto){
        pizzaRepository.updatePrice(dto);
        this.sendEmail();
    }

    //Método de prueba para retornar una excepción, al momento de simular "enviar" un correo
    //cuando se actualice el precio de una Pizza (Se usa en el método anterior)
    private void sendEmail(){
        throw new EmailApiException();
    }

    //--------------DELETE-------------------//
    public void delete(int idPizza){
        pizzaRepository.deleteById(idPizza);
    }

    //--------------VALIDATIONS-------------------//
    public boolean exist(int idPizza){
        return pizzaRepository.existsById(idPizza);
    }
}