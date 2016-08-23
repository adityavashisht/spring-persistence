package com.spring.persistence.mapper;

import com.spring.persistence.common.SearchCriteria;
import com.spring.persistence.domain.CustomerProfile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by vashishta on 8/23/16.
 */
public interface CustomerMapper {

    CustomerProfile getById(@Param("abc") Long id);

    /**
     *
     * @param searchCriteria
     * @return CustomerProfile
     */
    List<CustomerProfile> search(@Param("searchCriteria") SearchCriteria searchCriteria);


    /**
     *
     * @param customerProfile
     */
    void createProfile(@Param("customerProfile") CustomerProfile customerProfile);
}
