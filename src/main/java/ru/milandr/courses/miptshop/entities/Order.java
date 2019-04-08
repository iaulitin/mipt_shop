package ru.milandr.courses.miptshop.entities;

import lombok.*;
import ru.milandr.courses.miptshop.entities.enums.OrderStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "ORDERS")
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "user")
public class Order {

    @Id
    @Column(name = "ID")
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq_gen")
    @SequenceGenerator(name = "order_seq_gen", sequenceName = "order_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "USER_ID")
    @NotNull
    @Getter
    @Setter
    private Long userId;

    // @JoinColumn indicates that this entity is the owner of the relationship
    // (that is: the corresponding table has a column with a foreign key to the
    // referenced table), whereas the attribute mappedBy indicates that the
    // entity in this side is the inverse of the relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    @Getter
    @Setter
    private User user;

    @Column(name = "STATUS_CODE")
    private short statusCode;

    @Column(name = "CHANGE_DATE_TIME")
    @Getter
    @Setter
    private LocalDateTime changeDateTime;

    public OrderStatus getStatus() {
        return OrderStatus.parse(this.statusCode);
    }

    public void setStatus(OrderStatus orderStatus) {
        this.statusCode = orderStatus.getValue();
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @Getter
    @Setter
    private List<OrderGood> orderGoods;

    public Order(Long id, Long userId, OrderStatus orderStatus,
                 LocalDateTime changeDateTime,
                 List<OrderGood> orderGoods) {
        this.id = id;
        this.userId = userId;
        this.statusCode = orderStatus.getValue();
        this.changeDateTime = changeDateTime;
        this.orderGoods = orderGoods;
    }
}