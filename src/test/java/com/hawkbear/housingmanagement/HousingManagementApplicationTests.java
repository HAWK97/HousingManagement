package com.hawkbear.housingmanagement;

import com.hawkbear.housingmanagement.mapper.HouseMapper;
import com.hawkbear.housingmanagement.pojo.House;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HousingManagementApplicationTests {

    @Autowired
    private HouseMapper houseMapper;

    @Test
    public void contextLoads() {
        House house = new House();
        house.setDescription("hahah");
        houseMapper.insert(house);
    }

}

