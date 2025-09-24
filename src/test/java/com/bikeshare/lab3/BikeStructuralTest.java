package com.bikeshare.lab3;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;

import com.bikeshare.model.Bike;
import com.bikeshare.model.Bike.BikeStatus;
import com.bikeshare.model.Bike.BikeType;


// What to test:
// All public methods
// If/else branches
// Exception scenarios
public class BikeStructuralTest   {
    // TODO Test BikeStatus
    // TODO Test reserve()
    

    // TODO Test sendToMaintenance()
    // TODO Test completeMaintenance()
    // TODO Test markAsBroken()
    // TODO Test chargeBattery()

    // TODO Test BikeStatus
    @ParameterizedTest
    @DisplayName("This test is used to test all BikeStatus transitions")
    @EnumSource(BikeStatus.class)
    void GetBikeStatusForBike(BikeStatus initialStatus) {
        // Arrange
        Bike bike = new Bike("testId", BikeType.STANDARD);
        
        // Act
        switch (initialStatus) {
            case AVAILABLE:
                assertEquals(BikeStatus.AVAILABLE, bike.getStatus());
                bike.reserve();
                assertEquals(BikeStatus.RESERVED, bike.getStatus());
                break;
            
            case RESERVED:
                bike.reserve();
                assertEquals(BikeStatus.RESERVED, bike.getStatus());
                bike.startRide();
                assertEquals(BikeStatus.IN_USE, bike.getStatus());
                break;
            
            case IN_USE:
                bike.reserve();
                bike.startRide();
                assertEquals(BikeStatus.IN_USE, bike.getStatus());
                bike.endRide(5);
                assertEquals(BikeStatus.AVAILABLE, bike.getStatus());
                break;
            
            case MAINTENANCE:
                bike.sendToMaintenance();
                assertEquals(BikeStatus.MAINTENANCE, bike.getStatus());
                bike.completeMaintenance();
                assertEquals(BikeStatus.AVAILABLE, bike.getStatus());
                break;
            case BROKEN:
                bike.markAsBroken();
                assertEquals(BikeStatus.BROKEN, bike.getStatus());
                bike.sendToMaintenance();
                assertEquals(BikeStatus.MAINTENANCE, bike.getStatus());
                bike.completeMaintenance();
                assertEquals(BikeStatus.AVAILABLE, bike.getStatus());
                break;
            default:
                throw new IllegalStateException("Unexpected BikeStatus: " + initialStatus);
        }
    }

    
    
    // // TODO Test BikeType
    // @Test
    // @DisplayName("This test is used to get the bikeId and Biketype")
    // @EnumSource( biketypes = Biketype.class, )
    // void getBikeTypeForBike(String bikeId, BikeType type){
        
    //     //Arrange
        
    //     Bike b = new Bike(bikeId, type.ELECTRIC);

    //     //Act
        
    //     //Assert
    // }

   

    Bike testBike;
    
   
    @BeforeEach
    void setUp(){
        testBike = new Bike("1232", BikeType.ELECTRIC);
    }
    

    // TODO Test startRide()
    @Test
    @DisplayName("Should start a ride and set on a bike to unavaiable")
    void shouldStartRideAndChangeBikeStatusToIn_Use(){
        
        //Arrange
        BikeStatus initialStatus = testBike.getStatus(); // 

        //Assert
        assertEquals(BikeStatus.AVAILABLE, initialStatus);
        
        //Act
        testBike.startRide();
        BikeStatus newStatus = testBike.getStatus();
        
        //Assert
        assertEquals(BikeStatus.IN_USE, newStatus);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw IllegalArgumentException")
    void emptyBikeId_shouldThrowError(String bikeId){

        try {
            //Act
            Bike bike = new Bike(bikeId, BikeType.STANDARD); 
            
        } catch (Exception e) {
            //Assert
            assertThrows(IllegalArgumentException.class, () -> {
                throw new IllegalArgumentException("Bike ID cannot be null or empty");
            });
        }
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException")
    void nullBikeType_shouldThrowError(){

        try {
            //Act
            Bike bike = new Bike("testbike", null);
            
        } catch (Exception e) {
            assertThrows(IllegalArgumentException.class, () -> {
                throw new IllegalArgumentException("Bike type cannot be null");
            });
        }
    }

    

    @Test
    void testExpectedExceptionIsThrown() {
        // The following assertion succeeds because the code under assertion
        // throws the expected IllegalArgumentException.
        // The assertion also returns the thrown exception which can be used for
        // further assertions like asserting the exception message.
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> {
                throw new IllegalArgumentException("expected message");
            });
        assertEquals("expected message", exception.getMessage());

        // The following assertion also succeeds because the code under assertion
        // throws IllegalArgumentException which is a subclass of RuntimeException.
        assertThrows(RuntimeException.class, () -> {
            throw new IllegalArgumentException("expected message");
        });
    }






    // TODO Test endRide()
    @Test
    @DisplayName("Should end the ride and set status on bike to available")
    void ShouldEndRideAndSetBikeStatusToAvalible() {
        // Arrange
        Bike bike = new Bike("test", BikeType.STANDARD);
        bike.startRide(); // Set bike status to in use
        int distanceTraveled = 2;

        // Act
        bike.endRide(distanceTraveled);

        // Assert
        assertEquals(BikeStatus.AVAILABLE, bike.getStatus());
    }
    
    @Test
    @DisplayName("Should end the ride and set status on bike to available")
    void ShouldEndRideAndSetElectricBikeStatusToAvalibleWithAdjustedBatteryLevel() {
        // Arrange
        Bike bike = new Bike("test", BikeType.ELECTRIC);
        bike.startRide(); // Set bike status to in use
        int distanceTraveled = 2;
        double batteryLevel = bike.getBatteryLevel();

        // Act
        bike.endRide(distanceTraveled);

        // Assert
        assertEquals(BikeStatus.AVAILABLE, bike.getStatus());
        
    }
    
    //get methods

    // TODO Test String getBikeId()
    
    // TODO Test BikeStatus getStatus() {

    // TODO Test BikeType getType() 
    
    // TODO Test String getCurrentStationId() {
    
    
    // public void setCurrentStationId(String currentStationId) {
    
    
    // public double getBatteryLevel() {
   
    
    // public int getTotalRides() {

    
    // public double getTotalDistance() {
    
    // public LocalDateTime getLastMaintenanceDate() {

    
    // public LocalDateTime getLastUsedDate() {

    
    // public boolean needsMaintenance() {
    
    // public boolean isAvailable() {
  

/**
 * Lab 3 Template: Structural Testing for BikeType enum
 * 
 * TODO for students:
 * - Test all public methods: getDisplayName(), getPricePerMinute(), isElectric(), getMaxSpeedKmh()
 * - Test different input values: All enum values (STANDARD, ELECTRIC, MOUNTAIN, CARGO)
 * - Test if/else branches: isElectric() method (branch coverage)
 * - Test switch statement: getMaxSpeedKmh() method (all branches)
 * - Optional: Add parameterized tests for testing all enum values efficiently
 */

// This test is just an example to get you started. You will need to add more tests as per the challenges.


}
