package com.lukhol.hibernate.inheritance.tableperclass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAggregatorRepository extends JpaRepository<UserAggregator, Long> {
}
