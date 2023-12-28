package androidsamples.java.dicegames;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("ConstantConditions")
public class TwoOrMoreViewModel extends ViewModel {

  int tBal,bet;
  GameType game;
  List<Integer> values_of_dice;
  List<Die> game_die;
  GameResult result;

  public TwoOrMoreViewModel() {
    tBal=0;
    bet=0;
    values_of_dice= new ArrayList<>();
    game_die= new ArrayList<>();
    game=GameType.NONE;
    result=GameResult.UNDECIDED;
  }

  public int balance() {
    return tBal;
  }

  public void setBalance(int balance) {
    tBal = balance;
  }

  public GameType gameType() {
    return game;
  }


  public void setGameType(GameType gameType) {
    game=gameType;
  }

  public int wager() {
    return bet;
  }

  public void setWager(int wager) {
    bet=wager;
  }

  public boolean isValidWager() {
    if(gameType()==GameType.TWO_ALIKE)
    {
      return tBal >= (bet * 2);
    }
    if(gameType()==GameType.THREE_ALIKE)
    {
      return tBal >= (bet * 3);
    }
    if(gameType()==GameType.FOUR_ALIKE)
    {
      return tBal >= (bet * 4);
    }
    return false;
  }

  public List<Integer> diceValues()
  {
    for(int i=0;i<game_die.size();i++)
    {
      values_of_dice.set(i,game_die.get(i).value());
    }
    return values_of_dice;
  }

  public void addDie(Die d) {
    game_die.add(d);
    values_of_dice.add(0);
  }

  public GameResult play() throws IllegalStateException {
    if(game==GameType.NONE)
      throw new IllegalStateException("Game Type not set, can't play!");
    if(wager()==0)
      throw new IllegalStateException("Wager not set, can't play!");
    if((gameType()==GameType.FOUR_ALIKE && game_die.size()<4) || (gameType()==GameType.THREE_ALIKE && game_die.size()<3) || (gameType()==GameType.TWO_ALIKE && game_die.size()<2))
      throw new IllegalStateException("Not enough dice, can't play!");
    if(game_die.size()>4)
      throw new IllegalStateException("Cannot set more than 4 die");

    HashMap<GameType,Integer> mp=new HashMap<>();
    mp.put(GameType.THREE_ALIKE,3);
    mp.put(GameType.FOUR_ALIKE,4);
    mp.put(GameType.TWO_ALIKE,2);
    HashMap<Integer,Integer> frequency=new HashMap<>();
    int ma=0;
    for(int i=0;i<game_die.size();i++)
    {
      game_die.get(i).roll();
      int temp=diceValues().get(i);
      if(frequency.containsKey(temp))
        frequency.put(temp,frequency.get(temp)+1);
      else
        frequency.put(temp,1);
      ma=Math.max(ma,frequency.get(temp));
    }
    if(ma >= mp.get(game))
    {
      tBal+=(mp.get(game)*bet);
      return GameResult.WIN;
    }
    else {
      tBal-=(mp.get(game)*bet);
      return GameResult.LOSS;
    }
  }
}
