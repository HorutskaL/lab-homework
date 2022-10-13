package com.epam.selectioncommittee.validation;

import com.epam.selectioncommittee.dto.validation.CustomValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomValidatorTest {
    @InjectMocks
    private CustomValidator customValidator;
    @Mock
    private ConstraintValidatorContext context;

    @Test
    void isValidTest() {
        String password = "1passworD";
        boolean isValidResult = customValidator.isValid(password, context);
        assertTrue(isValidResult);
    }

    @Test
    void isNotValidTest() {
        String password = "11111";
        boolean isValidResult = customValidator.isValid(password, context);
        assertFalse(isValidResult);
    }

    @Test
    void emptyPasswordTest() {
        String password = "";
        boolean isValidResult = customValidator.isValid(password, context);
        assertFalse(isValidResult);
    }
}
