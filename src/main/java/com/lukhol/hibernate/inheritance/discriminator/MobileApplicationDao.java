package com.lukhol.hibernate.inheritance.discriminator;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileApplicationDao extends JpaRepository<MobileApplication, Long> {
}
