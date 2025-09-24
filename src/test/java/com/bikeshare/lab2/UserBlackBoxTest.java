package com.bikeshare.lab2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.lang.reflect.Executable;
import java.nio.channels.MembershipKey;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.bikeshare.model.MembershipType;
import com.bikeshare.model.User;

/**
 * Lab 2 Template: Black Box Testing for User class
 * 
 
 * 
 * TODO for students:
 * - Challenge 2.1: Add Equivalence Partitioning tests for email validation, name, telephone number (With GenAI help), and fund addition
 * - Challenge 2.2: Add Boundary Value Analysis tests for fund addition
 * - Challenge 2.3: Add Decision Table tests for phone number validation
 * - Optional Challenge 2.4: Add error scenario tests
 */

// This test is just an example to get you started. You will need to add more tests as per the challenges.
@DisplayName("Verify name handling in User class")
class UserBlackBoxTest {
    
    @Test
    @DisplayName("Should store and retrieve user names correctly")
    void shouldStoreAndRetrieveUserNamesCorrectly() {
        // Arrange - Set up test data
        String expectedFirstName = "John";
        String expectedLastName = "Doe";
        String validEmail = "john.doe@example.com";
        String validPersonnummer = "901101-1237"; // Valid Swedish personnummer
        
        // Act - Execute the method under test
        User user = new User(validPersonnummer, validEmail, expectedFirstName, expectedLastName);
        String actualFirstName = user.getFirstName();
        String actualLastName = user.getLastName();
        String actualFullName = user.getFullName();
        
        // Assert - Verify the expected outcome
        assertNotNull(user, "User should be created successfully");
        assertEquals(expectedFirstName, actualFirstName, "First name should match");
        assertEquals(expectedLastName, actualLastName, "Last name should match");
        assertEquals("John Doe", actualFullName, "Full name should be formatted correctly");
    }
    
    // TODO: Challenge 2.1 - Add Equivalence Partitioning tests for email validation
    // Hint: Test valid emails (user@domain.com) and invalid emails (missing @, empty, etc.)

    @Test
    @DisplayName ("Tests if email has a blank space")
    void emailHasBlankSpace(){

        //Arrange 
        String email = " testIfEmailHasNoSpace@gmail.com"; 
        Boolean hasSpace = false; 
        
        //Act
        if (email.contains(" ")) {
            hasSpace = true;
        }
        
        //Assert
        assertTrue(hasSpace);
    }

    @Test
    @DisplayName ("Checks if the email is missing @")
    void emailMissingSeparation_ShouldBeFalse() {
        //Arrange
        String email = "testEmailgmail.com";
        Boolean hasSeparationCharacter = false;

        //Act
        if (email.contains("@")) {
            hasSeparationCharacter = true;
        }

        //Assert
        assertFalse(hasSeparationCharacter);      
    }

    @Test
    @DisplayName("Checks if name has minimum characters")
    void nameHasMinAmountOfCharacters_shouldBeTrue(){
         
        //arrange
        String name = "El";
        Boolean hasMinCharacters = true;

        //Act
        if (name.length() < 2) {
            hasMinCharacters = false;
        }

        //Assert
        assertTrue(hasMinCharacters);

        
    }

    @Test
    @DisplayName("Check if the amount of characters is out of range")
    void nameHasNotEnoughCharachters_shouldBeFalse() {
        // Arrange
        String name = "L";
        Boolean hasEnoughCharacters = true;
        
        // Act
        if (name.length() < 2) {
            hasEnoughCharacters = false;
        }

        // Assert
        assertFalse(hasEnoughCharacters);
    }

    @Test
    @DisplayName(" Checks if user has the minimum balance for funds")
    void fundsHasTheMinimumValue_shouldBeTrue(){
    
        // Arrange
        //double studentDiscount = MembershipType.STUDENT.getDiscountRate();
        String expectedFirstName = "John";
        String expectedLastName = "Doe";
        String validEmail = "john.doe@example.com";
        String validPersonnummer = "901101-1237";
        User user = new User(validPersonnummer, validEmail, expectedFirstName, expectedLastName);
        Double balance = 0.10;
        Boolean hasTheMinimumValue = false;

        // Act
        if (balance >= 0.10) {
            hasTheMinimumValue = true;
        };
        
        // Assert
        assertTrue(hasTheMinimumValue);
    
    }

    @Test
    @DisplayName(" Checks if user has below amonut of balance for funds")
    void fundWithInvalidAmounts_shouldBeTrue(){
    
        // Arrange
        String expectedFirstName = "John";
        String expectedLastName = "Doe";
        String validEmail = "john.doe@example.com";
        String validPersonnummer = "901101-1237";

        User user = new User(validPersonnummer, validEmail, expectedFirstName, expectedLastName);
        Double accountBalance = user.getAccountBalance();
         user.addFunds(0.1);
        Boolean hasInvalidValue = false;

        // Act
        if (accountBalance < 0.10 ) {
            hasInvalidValue = true;
        };

        //Assert
        assertTrue(hasInvalidValue);

    }
    
    @Test
    @DisplayName("Check if the user can add a negative amount of credits.")
    void addFundHasNegativeAmount_shouldBeTrue(){
        // Arrange
        String expectedFirstName = "John";
        String expectedLastName = "Doe";
        String validEmail = "john.doe@example.com";
        String validPersonnummer = "901101-1237";
        User user = new User(validPersonnummer, validEmail, expectedFirstName, expectedLastName);
        Double amount = -2.0;
        Boolean amountIsNegative = false;

        //Act
        try{
         user.addFunds(amount);

         } catch (IllegalArgumentException e) {
             amountIsNegative = true;
         }

        //Assert
        assertTrue(amountIsNegative);
        
    }

    @Test
    @DisplayName("Try to add funds less than 0.10 SEK")
    void addFundWithMinimumEdgeCase() {
        // Arrange
        String expectedFirstName = "John";
        String expectedLastName = "Doe";
        String validEmail = "john.doe@example.com";
        String validPersonnummer = "901101-1237";
        Double amount = 0.01;

        // Act
        User user = new User(validPersonnummer, validEmail, expectedFirstName, expectedLastName);
        user.addFunds(amount);
        Double funds = user.getAccountBalance();

        // Act
        assertEquals(0.01, funds);
    }

    @Test
    @DisplayName("Try to add funds maximum of 1000 SEK")
    void addFundWithMaximumEdgeCase() {
        // Arrange
        String expectedFirstName = "John";
        String expectedLastName = "Doe";
        String validEmail = "john.doe@example.com";
        String validPersonnummer = "901101-1237";
        Double amount = 1000.00;

        // Assert
        User user = new User(validPersonnummer, validEmail, expectedFirstName, expectedLastName);
        user.addFunds(amount);
        Double funds = user.getAccountBalance();

        // Act
        assertEquals(1000, funds);
    }
    
    // Testcases generated by Github Copilot GPT-4.1
    // --- Telephone Number Validation Tests (Lab 2, FR-1.1) ---

    @ParameterizedTest
    @CsvSource({
        // Valid Swedish numbers (national)
        "0701234567,true",
        "0739876543,true",
        // Valid Swedish numbers (international)
        "+46701234567,true",
        "+46739876543,true",
        // Invalid: wrong prefix
        "0601234567,false",
        "+4681234567,false",
        // Invalid: too short
        "070123456,false",
        // Invalid: too long
        "070123456789,false",
        // Invalid: contains letters
        "07A1234567,false",
        // Invalid: empty
        //" ,false",
        // Invalid: null (handled as empty)
        //",false"
    })
    @DisplayName("Equivalence Partitioning: Phone number format acceptance")
    void testPhoneNumberEquivalence(String input, boolean shouldAccept) {
        String validPersonnummer = "901101-1237";
        String validEmail = "john.doe@example.com";
        User user = new User(validPersonnummer, validEmail, "John", "Doe");
        if (shouldAccept) {
            // Should not throw
            try {
                user.setPhoneNumber(input);
                assertEquals(input == null || input.trim().isEmpty() ? null : input.replaceAll("[\\s()-]", ""), user.getPhoneNumber());
            } catch (Exception e) {
                fail("Expected valid phone number: " + input);
            }
        } else {
            assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber(input));
        }
    }

    @Test
    @DisplayName("Boundary Value: Phone number length (min 10, max 12 with +46)")
    void testPhoneNumberBoundaryValues() {
        String validPersonnummer = "901101-1237";
        String validEmail = "john.doe@example.com";
        User user = new User(validPersonnummer, validEmail, "John", "Doe");
        // Exactly 10 digits (national)
        assertDoesNotThrow(() -> user.setPhoneNumber("0701234567"));
        // 9 digits (too short)
        assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber("070123456"));
        // 11 digits (too long for national)
        assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber("07012345678"));
        // Exactly 12 chars with +46 (international)
        assertDoesNotThrow(() -> user.setPhoneNumber("+46701234567"));
        // 13 chars (too long)
        assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber("+467012345678"));
    }

    @ParameterizedTest
    @CsvSource({
        // Decision table: spaces, dashes, parentheses should be ignored
        "070-123 45 67,true",
        "+46 70 123 45 67,true",
        "(070)1234567,true",
        // Invalid: special chars not allowed
        "070*1234567,false",
        // Invalid: double plus
        "++46701234567,false"
    })
    
    @DisplayName("Decision Table: Phone number normalization and rejection cases")
    void testPhoneNumberNormalization(String input, boolean shouldAccept) {
        String validPersonnummer = "901101-1237";
        String validEmail = "john.doe@example.com";
        User user = new User(validPersonnummer, validEmail, "John", "Doe");
        if (shouldAccept) {
            assertDoesNotThrow(() -> user.setPhoneNumber(input));
        } else {
            assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber(input));
        }
    }
    // --- End Telephone Number Tests ---
    
    // TODO: Challenge 2.2 - Add Boundary Value Analysis tests for fund addition
    // Hint: Test minimum (0.01), maximum (1000.00), and invalid amounts (0, negative, > 1000)
    
    //**Challenge 2.3 — Decision-table tests:** Implement a compact set for membership → discount. 

    @Test
    @DisplayName("Check if a Student memebership has any discount")
    void studentHasDiscount(){

        //Arrange
        MembershipType studentMemberShip = MembershipType.STUDENT;
        //Act
        Boolean hasDiscount = studentMemberShip.hasDiscount();
        //Assert
        assertTrue(hasDiscount);
    }

    @ParameterizedTest
    @CsvSource({
        "BASIC,false",
        "PREMIUM,true",
        "VIP,true",
        "STUDENT,true",
        "CORPORATE,true"
    })
    @DisplayName("Test if membership has discount")
    void hasDiscount(String type, boolean expectedHasDiscount) {

        //Act
        MembershipType membershipType = MembershipType.valueOf(type);
        boolean hasDiscount = membershipType.hasDiscount();

        //Assert
        assertEquals(expectedHasDiscount, hasDiscount);
    }
    
    // TODO: Challenge 2.4 - Add error scenario tests
    // Hint: Test insufficient balance, invalid inputs, state violations

}
