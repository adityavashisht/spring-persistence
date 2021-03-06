package com.spring.persistence.dao;

import com.spring.persistence.domain.CustomerProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;


/**
 * Created by vashishta on 8/20/16.
 */
@Component
public class CustomerDaoImpl extends JdbcDaoSupport implements CustomerDao {


    @Autowired
    public CustomerDaoImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public void createProfile(CustomerProfile customerProfile) {

        Long id = getJdbcTemplate().queryForObject("select nextval('customer_profile_seq');", Long.class);

        String sql = "INSERT INTO customer_profile " +
                "(CUSTOMER_PROFILE_ID, EMAIL, FIRST_NAME,LAST_NAME, STREET, CITY) VALUES (?, ?, ?, ?, ? ,?)";

        int rows = getJdbcTemplate().update(sql, new Object[]{id,
                customerProfile.getEmail(),
                customerProfile.getFirstName(),
                customerProfile.getLastName(),
                customerProfile.getStreet(),
                customerProfile.getCity()

        });

        System.out.println("Rows updated " + rows);


    }


    public void updateProfile(CustomerProfile customerProfile) {

    }


    public CustomerProfile withCustomMapper(Long id) {


        final String SELECT = "select customer_profile_id as id, email as email from customer_profile where customer_profile_id = ?"
                 + " "
                  + " "
                ;

        CustomerProfile profile = getJdbcTemplate().queryForObject(SELECT, new Object[]{id},
                new CustomerProfileRowMapper());

        return profile;

    }

    public CustomerProfile customerProfile(Long id) {

        final String SELECT = "select customer_profile_id as id, email as email from customer_profile where customer_profile_id = ?";

        CustomerProfile profile = getJdbcTemplate().queryForObject(SELECT, new Object[]{id},
                new BeanPropertyRowMapper<CustomerProfile>(CustomerProfile.class));

        return profile;

    }

    public List<CustomerProfile> getAll() {

        final String SELECT = "select customer_profile_id as id, email as email from customer_profile";

        List<CustomerProfile> profiles = getJdbcTemplate().query(SELECT, new Object[]{},
                new BeanPropertyRowMapper<CustomerProfile>(CustomerProfile.class));

        return profiles;

    }


    @PostConstruct
    public void init() {
        if(getDataSource() == null) {
            throw new IllegalArgumentException("Datasource must be provided");
        }
    }

}
