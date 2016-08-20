package com.spring.persistence.dao;

import com.spring.persistence.domain.CustomerProfile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by vashishta on 8/20/16.
 */
public class CustomerProfileRowMapper implements RowMapper<CustomerProfile> {

    public CustomerProfile mapRow(ResultSet rs, int rowNum) throws SQLException {

        String email = rs.getString("email");
        Long id = rs.getLong("id");

        CustomerProfile customerProfile = new CustomerProfile();
        customerProfile.setEmail(email);
        customerProfile.setId(id);

        return  customerProfile;
    }
}
