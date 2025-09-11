package com.travelbooking.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="products")
public class Product extends Base{
    @Pattern(regexp = "^\\S.*", message = "The name can't start with  an invalid character")
    @NotEmpty
    @Size(min = 3, max = 50, message = "The name must be between 3 a 50 chars long")
    @Column(name="name")
    private String name;

    @Pattern(regexp = "^\\S.*", message = "The title can't start with  an invalid character")
    @NotEmpty
    @Size(min = 3, max = 50, message = "The title must be between 3 a 50 chars long")
    @Column(name="title")
    private String title;

    @Pattern(regexp = "^\\S.*", message = "The description can't start with  an invalid character")
    @NotEmpty
    @Size(min = 10, max = 50, message = "The description must be between 10 a 50 chars long")
    @Column(name="description")
    private String description;

    @Pattern(regexp = "^\\S.*", message = "The address can't start with  an invalid character")
    @NotEmpty
    @Size(min = 10, max = 50, message = "The address must be between 10 a 50 chars long")
    @Column(name="address")
    private String address;

    @NotEmpty(message = "You must enter the latitude")
    @Column(name="latitude")
    private String latitude;

    @NotEmpty(message = "You must enter the longitude")
    @Column(name="longitude")
    private String longitude;

    @NotNull(message = "The category ID can't be null")
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotEmpty(message = "The images can't be null")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private List<Image> image;

    @NotNull(message = "The characteristics can't be null")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "characteristic_id", referencedColumnName = "id", nullable = false)
    private Characteristic characteristic;

    @NotNull(message = "The city ID can't be null")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    private City city;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Reservation> reservations;

    @Column(name = "score")
    private Double score;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Favorite> favorites;

    //Equals y HashCode para comparar objetos por id
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() { return Objects.hash(getId());}

}
