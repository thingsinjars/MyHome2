package com.myhome.controllers.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * extends AuthenticationException and provides additional logging information when
 * a user is not found using their email address.
 */
@Slf4j
public class UserNotFoundException extends AuthenticationException {
  public UserNotFoundException(String userEmail) {
    super();
    log.info("User not found - email: " + userEmail);
  }
}
