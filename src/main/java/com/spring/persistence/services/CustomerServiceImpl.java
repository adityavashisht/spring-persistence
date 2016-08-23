package com.spring.persistence.services;

import com.spring.persistence.dao.CustomerDao;
import com.spring.persistence.domain.CustomerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by vashishta on 8/23/16.
 */
@Component("customerService")

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;



    // Transactions to be started here - starts when the method is invoked
    // Ends when the method returns
    public void createProfile(CustomerProfile customerProfile) {

        // Create a profile
        // Send email to customer
        // Send email to store owner
        // Set new coupons


        customerDao.createProfile(customerProfile);
    }

    public CustomerProfile withCustomMapper(Long id) {
        return customerDao.withCustomMapper(id);
    }

    public CustomerProfile customerProfile(Long id) {
        return customerDao.customerProfile(id);
    }

    public List<CustomerProfile> getAll() {
        return customerDao.getAll();
    }


}
