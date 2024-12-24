package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GenericGenerator(name = "TimestampGenerator", type = TimestampIdGenerator.class)
    @GeneratedValue(generator = "TimestampGenerator")
    private long id;

    private String name;
//    private String email;
//
//    @OneToMany(mappedBy = "customer")
//    private List<Order> orders;

}
