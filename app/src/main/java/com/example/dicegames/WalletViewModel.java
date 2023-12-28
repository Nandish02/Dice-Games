package androidsamples.java.dicegames;


import androidx.lifecycle.ViewModel;

public class WalletViewModel extends ViewModel {

  int coins_Balance, set_die;
  Die dice;
  int increment_value,win_value;

  public WalletViewModel() {
    coins_Balance=0;
    set_die =0;
  }

  public int balance() {
    return coins_Balance;
  }

  public void setBalance(int amount) {
    coins_Balance=amount;
  }

  public void rollDie(){
    if(set_die ==0)
      throw new IllegalStateException();
    dice.roll();
    if(dice.value()==win_value)
      coins_Balance+=increment_value;
  }

  public int dieValue() {

    return dice.value();
  }

  public void setIncrement(int increment) {
    increment_value=increment;
  }

  public void setWinValue(int winValue) {
    win_value=winValue;
  }

  public void setDie(Die d) {
    set_die =1;  //die is set
    dice=d;
  }
}
