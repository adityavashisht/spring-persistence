package com.spring.persistence.services;

import com.spring.persistence.domain.CustomerProfile;

import java.util.List;

/**
 * Created by vashishta on 8/23/16.
 */
public interface CustomerService {

    void createProfile(CustomerProfile customerProfile);

    CustomerProfile withCustomMapper(Long id);

    CustomerProfile customerProfile(Long id);

    List<CustomerProfile> getAll();
}
