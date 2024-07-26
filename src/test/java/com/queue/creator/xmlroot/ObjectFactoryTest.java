package com.queue.creator.xmlroot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ObjectFactoryTest {

    @Test
    public void testCreateStudent() {

        ObjectFactory factory = new ObjectFactory();

        Student student = factory.createStudent();

        assertNotNull(student, "The Student object should not be null");
    }
}
