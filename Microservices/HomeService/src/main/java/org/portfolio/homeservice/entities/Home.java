package org.portfolio.homeservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "homes")
@SQLDelete(sql="UPDATE homes set is_deleted=true where id=?")
@SQLRestriction("is_deleted=false")
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String city;
    private String street;
    private Integer bed;
    private String bath;
    private Integer sqft;
    private Integer price;
    private boolean isDeleted;
    @OneToMany(mappedBy = "home",cascade = CascadeType.ALL)
    private List<Image> images;
}
