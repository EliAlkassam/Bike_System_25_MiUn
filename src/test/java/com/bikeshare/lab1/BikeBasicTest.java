package com.bikeshare.lab1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.bikeshare.model.BikeType;


@DisplayName("class si..")
class BikeBasicTest {


    /// checks if first and second parameter has the same value
    @Test
    @DisplayName("The first test...") 
    void myFirstTest(){
    
        BikeType standardBike = BikeType.STANDARD;
    
        String bikeName = standardBike.getDisplayName();
    
        // Assert: Check if it's correct
        assertEquals("Standard Bike", bikeName); 
    }

    @Test
    @DisplayName("The second test...") 
    void learnArrangeActAssert(){
        //arrange
        String expectedMessage = "Eli";

        //act
        String actualMessage = "Eli";

        //assert
        assertEquals(expectedMessage, actualMessage);

    }

 @Test
    @DisplayName("Test bike types - simple version")
    void testBikeTypesSimple() {
        // TODO: Test BikeType.STANDARD
        // ARRANGE: Get a bike type using BikeType.STANDARD
        BikeType standardBike = BikeType.STANDARD;
        
        // ACT: Get its display name using getDisplayName()

        String myBikeName = standardBike.getDisplayName();

        
        
        // ASSERT: Check if it equals "Standard Bike"
        assertEquals("Standard Bike", myBikeName);
        
        // Try this: Change "Standard Bike" to something else and see the test fail!
    }

    @Test 
    @DisplayName("Learn different assertions")
    void learnDifferentAssertions(){
        
        BikeType electricBike = BikeType.ELECTRIC;
        BikeType moutainBike = BikeType.MOUNTAIN;

        //assertTrue() checks if something is true
        assertEquals("Electric Bike", electricBike.getDisplayName());

        assertTrue(electricBike != null);
        assertFalse(electricBike == null);
        assertNotNull(electricBike);

      
        assertTrue(electricBike.getDisplayName() != moutainBike.getDisplayName());
        assertFalse(electricBike == moutainBike);

        assertSame(electricBike.getPricePerMinute(), moutainBike.getPricePerMinute());


    }

}

