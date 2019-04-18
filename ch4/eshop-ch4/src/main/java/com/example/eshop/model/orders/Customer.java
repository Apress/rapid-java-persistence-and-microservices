package com.example.eshop.model.orders;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.example.eshop.converter.CustomerAddressConverter;
import com.example.eshop.model.type.CustomerAddress;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@Entity
@ToString(exclude = {"orders"})
@EqualsAndHashCode(exclude = {"orders"})
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long customerId;

	private String name, email, password;
	private LocalDateTime dateAdded;

	@Column
	@Convert(converter = CustomerAddressConverter.class)
	CustomerAddress customerAddress;

	//@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId")
	Set<Order> orders;

}
