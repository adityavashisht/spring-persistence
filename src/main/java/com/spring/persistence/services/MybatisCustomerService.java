package com.spring.persistence.services;

import com.spring.persistence.common.SearchCriteria;
import com.spring.persistence.domain.CustomerProfile;
import com.spring.persistence.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by vashishta on 8/23/16.
 */
@Component
public class MybatisCustomerService {

    @Autowired
    private CustomerMapper customerMapper;


    public CustomerProfile getById(Long id) {
        return customerMapper.getById(id);
    }

    public List<CustomerProfile> search(SearchCriteria searchCriteria) {
        return customerMapper.search(searchCriteria);
    }

    public void create(CustomerProfile customerProfile) {
        customerMapper.createProfile(customerProfile);
    }
}
