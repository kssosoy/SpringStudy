package jpabook.springbootjpa.repository;

import jpabook.springbootjpa.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
