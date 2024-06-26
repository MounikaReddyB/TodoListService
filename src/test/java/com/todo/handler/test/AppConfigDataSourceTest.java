package com.todo.handler.test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AppConfigDataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testDataSource() {
        assertNotNull(dataSource);
    }
}