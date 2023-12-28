package com.example.dicegames;

import java.util.Random;

/**
 * An implementation of a six-faced {@link Die} using {@link Random}.
 */
public class Die6 implements Die {
  private static final int FACES = 6;
  private Random rand;
  private int value;
  public Die6()
  {
    rand= new Random();
  }

  @Override
  public void roll()
  {
    value = rand.nextInt(FACES) + 1;
  }

  @Override
  public int value()
  {
    return value;
  }
}

