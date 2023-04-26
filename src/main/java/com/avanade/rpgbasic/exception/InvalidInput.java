package com.avanade.rpgbasic.exception;

import java.io.Serial;

public class InvalidInput extends RuntimeException {

   @Serial
   private static final long serialVersionUID = 7783074921912303615L;

   public InvalidInput(String message) {
      super(message);
   }

   public InvalidInput() {
      super("Invalid Payload");
   }
}