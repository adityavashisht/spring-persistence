package com.spring.persistence.test;

import com.spring.persistence.common.SearchCriteria;
import com.spring.persistence.domain.CustomerProfile;
import com.spring.persistence.services.CustomerService;
import com.spring.persistence.services.MybatisCustomerService;
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
public class MyBatisCustomerProfileTest {


    @Autowired
    private MybatisCustomerService mybatisCustomerService;



    @Test
    public void testFetch() {

        CustomerProfile profile = mybatisCustomerService.getById(4L);
        Assert.notNull(profile);
    }

    @Test
    public void search() {

        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setFirstName("Aditya");

        searchCriteria.setEmail("aditya@indasil.com");

        List<CustomerProfile> profiles  =  mybatisCustomerService.search(searchCriteria);
        Assert.notNull(profiles);
    }


}
