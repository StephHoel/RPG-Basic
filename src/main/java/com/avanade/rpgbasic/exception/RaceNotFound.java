package com.avanade.rpgbasic.exception;

import java.io.Serial;

public class RaceNotFound extends InvalidInput {
   @Serial
   private static final long serialVersionUID = -5868980605782668416L;

   public RaceNotFound(String message) {
      super(message);
   }
}
