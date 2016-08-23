package com.spring.persistence.dao;

import com.spring.persistence.domain.CustomerProfile;

import java.util.List;

/**
 * Created by vashishta on 8/23/16.
 */
public interface CustomerDao {

    void createProfile(CustomerProfile customerProfile);

    CustomerProfile withCustomMapper(Long id);

    CustomerProfile customerProfile(Long id);

    List<CustomerProfile> getAll();
}
