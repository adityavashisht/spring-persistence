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

    List<CustomerProfile> search(@Param("searchCriteria") SearchCriteria searchCriteria);
}
