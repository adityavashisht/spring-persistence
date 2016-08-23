package com.spring.persistence.test;

import com.spring.persistence.dao.CustomerDao;
import com.spring.persistence.dao.CustomerDaoImpl;
import com.spring.persistence.domain.CustomerProfile;
import com.spring.persistence.services.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by vashishta on 8/20/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-persistence.xml"})
public class CustomerProfileTest {


    @Autowired
    private CustomerService customerService;

    @Test
    public void testProfile() {

        CustomerProfile customerProfile = new CustomerProfile();
        customerProfile.setFirstName("Aditya");
        customerProfile.setLastName("Vashisht");
        customerProfile.setEmail("aditya@indasil.com");
        customerProfile.setStreet("Bloomfield");
        customerProfile.setCity("Ashburn");

        customerService.createProfile(customerProfile);

    }

    @Test
    public void testFetch() {

        CustomerProfile profile = customerService.customerProfile(4L);
        Assert.notNull(profile);
    }

    @Test
    public void testFetchWithMapper() {

        CustomerProfile profile = customerService.withCustomMapper(4L);
        Assert.notNull(profile);
    }

    @Test
    public void testAll() {

        List<CustomerProfile> profiles = customerService.getAll();
        Assert.notEmpty(profiles);
    }
}
