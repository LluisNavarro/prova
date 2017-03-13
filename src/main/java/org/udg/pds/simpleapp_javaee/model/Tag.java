package org.udg.pds.simpleapp_javaee.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Jesús on 06/03/2017.
 */

@Entity
public class Tag {
    public String name;
    public String description;

    public Tag() {

    }

    public Tag(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // This tells JAXB that this field can be used as ID
    // Since XmlID can only be used on Strings, we need to use LongAdapter to transform Long <-> String
    @Id
    // Don't forget to use the extra argument "strategy = GenerationType.IDENTITY" to get AUTO_INCREMENT
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "tags")
    private List<Task> tasks;
}
