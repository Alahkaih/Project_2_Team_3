package com.Project_2_Location_Search_API;

import com.Project_2_Location_Search_API.entities.LocationQuery;
import com.Project_2_Location_Search_API.repositories.LocationQueryRepository;
import com.Project_2_Location_Search_API.service.LocationQueryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class LocationQueryServiceTests {
    private LocationQueryService locationQueryService;
    private LocationQueryRepository mockLocationQueryRepository;

    @BeforeEach
    public void initBefore() {
        mockLocationQueryRepository = mock(LocationQueryRepository.class);
        locationQueryService = new LocationQueryService();
        locationQueryService.setLocationQueryRepository(mockLocationQueryRepository);
    }

    @Test
    public void shouldCreateNewSearch(){
        LocationQuery locationQuery = new LocationQuery(1, "spain", "unsafe",
                500, 300, 50, 20, 100);

        when(mockLocationQueryRepository.save(locationQuery)).thenReturn(locationQuery);
        Assertions.assertDoesNotThrow(() -> {
            mockLocationQueryRepository.save(locationQuery);
        });
    }

}
