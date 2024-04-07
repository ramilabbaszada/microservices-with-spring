package org.portfolio.homeservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Data
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private byte [] imageData;

    private String fileName;

    private Boolean is_deleted;

    @ManyToOne
    private Home home;

     @Override
    public String toString(){
        return fileName;
     }

}
