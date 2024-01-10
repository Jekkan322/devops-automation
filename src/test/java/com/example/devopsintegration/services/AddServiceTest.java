package com.example.devopsintegration.services;


import com.example.devopsintegration.model.Result;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddServiceTest {

    @Test
    public void addIntegers() {
        AddService service = new AddService();

        Result result = service.add(2, 3);

        assertEquals(result.getResult(), 5);
    }
}
