package com;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class PetsManagementServiceTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void test_ApplicationContextLoads_WhenServiceIsExecuted(){
        Assertions.assertNotNull(context);
    }

}
