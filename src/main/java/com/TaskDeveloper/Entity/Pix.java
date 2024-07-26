package com.TaskDeveloper.Entity;

import com.TaskDeveloper.TypesPix.TypesPix;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Transactional
@Entity
@Table(name = "pix")
public class Pix {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pixId;

    @Length(max = 140, message = "Max length 140 characters")
    @NotNull(message = "key pix cannot be nullish")
    @Column
    private String key_pix;

    @NotNull(message = "key Type cannot be Nullish")
    @Column
    @Enumerated(EnumType.STRING)
    private TypesPix key_type;

    @Column
    private LocalDateTime created_at;


    public Pix(Long pixId, String key_pix, TypesPix key_type, LocalDateTime created_at) {
        this.pixId = pixId;
        this.key_pix = key_pix;
        this.key_type = key_type;

    }

    public Pix(){}


    @PrePersist
    public void prePersist(){
        this.created_at = LocalDateTime.now();
    }

    public Long getPixId() {
        return pixId;
    }

    public void setPixId(Long pixId) {
        this.pixId = pixId;
    }

    public  String getKey_pix() {
        return key_pix;
    }

    public void setKey_pix (String key_pix) {
        this.key_pix = key_pix;
    }

    public  TypesPix getKey_type() {
        return key_type;
    }

    public void setKey_type(TypesPix key_type) {
        this.key_type = key_type;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

}
