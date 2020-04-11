package com.example.demo.restaurant.business;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ListMockTest {

    List<String> mockList = mock(List.class);

    @Test
    public void testSize() {
        when(mockList.size()).thenReturn(3);
        assertEquals(3, mockList.size());
    }

    @Test
    public void returnDifferentValues() {
        when(mockList.size())
                .thenReturn(4)
                .thenReturn(5);

        assertEquals(4, mockList.size());
        assertEquals(5, mockList.size());
    }

    @Test
    public void returnWithParameter() {
        when(mockList.get(0)).thenReturn("Jérôme");
        assertEquals("Jérôme", mockList.get(0));
        assertEquals(null, mockList.get(1));
    }

    @Test
    public void returnWithGenericParameter() {
        when(mockList.get(anyInt())).thenReturn("anyValue");
        assertEquals("anyValue", mockList.get(0));
        assertEquals("anyValue", mockList.get(100));
    }

    @Test
    public void verificationCallTimes() {
        String value1 = mockList.get(0);
        String value2 =  mockList.get(1);

        verify(mockList).get(0);
        verify(mockList, times(2)).get(anyInt());
        verify(mockList, atLeast(1)).get(anyInt());

    }
}
