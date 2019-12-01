package com.revature.exception;

/**
 * This is a custom exception class for when an object that does not exist in the database is
 * attempting to be updated.
 *
 * @author Jane Shin
 * @author Roberto Rodriguez
 */
public class UpdateNonexistentException extends RuntimeException {

  /**
   * This is a generated serialVersionUID.
   */
  private static final long serialVersionUID = 5799089161862177558L;

  public UpdateNonexistentException(String message) {
    super(message);
  }
}
