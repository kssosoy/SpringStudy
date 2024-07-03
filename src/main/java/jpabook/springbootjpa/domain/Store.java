package jpabook.springbootjpa.domain;

import jakarta.persistence.*;
import jpabook.springbootjpa.domain.common.BaseEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String score;

    @OneToMany(mappedBy="store", cascade=CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

}
