package com.hawkbear.housingmanagement;

import com.hawkbear.housingmanagement.service.HouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HousingManagementApplicationTests {

    @Autowired
    private HouseService houseService;

    @Test
    public void test01() {
        houseService.addHouse(10L, "test", "test", 1, "test", "test");
    }

}

