package com.travelbooking.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="categories")
public class Category extends Base {

    @Pattern(regexp = "^\\S.*", message = "The title can't start with  an invalid character")
    @NotEmpty(message = "The category must contain a title")
    @Size(min = 10, max = 30, message = "The title must be between 10 to 30 chars long")
    @Column(name="title")
    private String title;

    @Pattern(regexp = "^\\S.*", message = "The description can't start with  an invalid character")
    @NotEmpty(message = "The category must contain a title")
    @Size(min = 10, max = 300, message = "The description must be 10 to 30 chars long")
    @Column(name="description")
    private String description;

    @Column(name="image")
    private String image;

    //Equals y HashCode para comparar objetos por id
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Category that = (Category) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() { return Objects.hash(getId());}


}
