package com.sevenwonders.shared.domain.generic.utils;

import java.util.List;

public class Utils {

  private  Utils(){

  }


  public static <T> void validateNotNull(T value, String fieldName) {
    if (value == null) {
      throw new IllegalArgumentException(fieldName + " can't be null");
    }
  }

  public static void validateNotBlank(String value) {
    if (value.isBlank()) {
      throw new IllegalArgumentException(value + " can't be empty");
    }
  }

  public static void validateNotNegative(Integer value) {
    if (value < 0) {
      throw new IllegalArgumentException(value + " can't be negative");
    }
  }

  public static void validateNotEmpty(List<String> value) {
    if (value.isEmpty()) {
      throw new IllegalArgumentException(value + " can't be empty");
    }
  }

  public static void validateNotSpecialCharacters(String value) {
    if (!value.matches("^[a-zA-Z0-9\\s]+$")) {
      throw new IllegalArgumentException(value + " can't contain special characters");
    }
  }
}
