package com.stackdev.springnative.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackdev.springnative.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
