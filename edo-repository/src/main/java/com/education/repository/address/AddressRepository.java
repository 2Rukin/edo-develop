package com.education.repository.address;

import com.education.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository в "edo-repository", служит для отправки запросов к БД
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
