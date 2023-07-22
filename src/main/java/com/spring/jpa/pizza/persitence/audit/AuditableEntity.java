package com.spring.jpa.pizza.persitence.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

//@MappedSuperclass -> para identificar la clase qeu puede ser heredada por otras clases, en este caso las entity
@MappedSuperclass
public class AuditableEntity {
    @Column(name = "created_date")
    //@CreatedDate-> Anotación para la auditoria (fecha de creación)
    @CreatedDate
    //Ignorar los campos para que no se muestren en la respuesta json
    private LocalDateTime createDate;

    @Column(name = "modified_date")
    //@LastModifiedDate-> Anotación para la auditoria (fecha de modificación)
    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime modifiedDate;
}
