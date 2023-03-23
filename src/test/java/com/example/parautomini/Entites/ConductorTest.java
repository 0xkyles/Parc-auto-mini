package com.example.parautomini.Entites;

import com.example.parautomini.Repositories.DriverRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConductorTest {
    @Autowired
    private DriverRepository conductorRepository;

    @Test
    public void saveConductor() {

    }
}