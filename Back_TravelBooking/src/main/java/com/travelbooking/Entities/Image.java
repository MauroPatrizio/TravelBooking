package com.travelbooking.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name="images")
public class Image extends Base{

    @NotEmpty(message = "The image must contain a title")
    @Size(min = 3, max = 30, message = "The image title must be between 3 and 30 chars long")
    @Column(name="title")
    private String title;

    @NotEmpty(message = "The image must contain an url")
    @Size(min = 3, max = 500, message = "The image url must be between 3 and 500 chars long")
    @Column(name="url")
    private String url;

    //Equals y HashCode para comparar objetos por id
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Image that = (Image) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() { return Objects.hash(getId());}
}
