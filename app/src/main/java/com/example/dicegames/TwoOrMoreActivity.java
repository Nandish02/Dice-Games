package com.example.dicegames;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class TwoOrMoreActivity extends AppCompatActivity {

  static final String TWO_BALANCE = "TWO_BALANCE";
  private TextView tBalance;
  private TextView die1, die2, die3, die4;
  private EditText wager;
  TwoOrMoreViewModel obj2;

  @SuppressLint("SetTextI18n")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_two_or_more);

    tBalance = findViewById(R.id.txt_balance_twoormore);
    RadioButton r1 = findViewById(R.id.btn_2alike);
    RadioButton r2 = findViewById(R.id.btn_3alike);
    wager = findViewById(R.id.edit_wager);
    Button go = findViewById(R.id.btn_go);
    die1 = findViewById(R.id.txt_die1);
    die2 = findViewById(R.id.txt_die2);
    die3 = findViewById(R.id.txt_die3);
    die4 = findViewById(R.id.txt_die4);
    Button back = findViewById(R.id.btn_back);
    Button info_btn = findViewById(R.id.btn_info);
    obj2 = new TwoOrMoreViewModel();
    obj2.setBalance(getIntent().getIntExtra(WalletActivity.TOTAL_BALANCE, 0));
    tBalance.setText(Integer.toString(obj2.balance()));
    Die d1 = new Die6();
    Die d2 = new Die6();
    Die d3 = new Die6();
    Die d4 = new Die6();
    obj2.addDie(d1);
    obj2.addDie(d2);
    obj2.addDie(d3);
    obj2.addDie(d4);
    die1.setText("0");
    die2.setText("0");
    die3.setText("0");
    die4.setText("0");
    go.setOnClickListener(v -> {
      if (r1.isChecked())
        obj2.setGameType(GameType.TWO_ALIKE);
      else if (r2.isChecked())
        obj2.setGameType(GameType.THREE_ALIKE);
      else
        obj2.setGameType(GameType.FOUR_ALIKE);
      if ((wager.getText().toString()).equals("")) {
        Toast.makeText(getApplicationContext(), "Wager not set, can't play!", Toast.LENGTH_SHORT).show();
        return;
      }
      int wage = Integer.parseInt(wager.getText().toString());
      if (wage <= 0) {
        Toast.makeText(getApplicationContext(), "Please enter a positive value", Toast.LENGTH_SHORT).show();
        return;
      }
      obj2.setWager(wage);
      if (!obj2.isValidWager()) {
        Toast.makeText(getApplicationContext(), "Insufficient balance", Toast.LENGTH_SHORT).show();
        return;
      }
      if (obj2.play() == GameResult.LOSS)
        Toast.makeText(getApplicationContext(), "Loss", Toast.LENGTH_SHORT).show();
      else
        Toast.makeText(getApplicationContext(), "Win", Toast.LENGTH_SHORT).show();
      die1.setText(Integer.toString(obj2.values_of_dice.get(0)));
      die2.setText(Integer.toString(obj2.values_of_dice.get(1)));
      die3.setText(Integer.toString(obj2.values_of_dice.get(2)));
      die4.setText(Integer.toString(obj2.values_of_dice.get(3)));
      tBalance.setText(Integer.toString(obj2.balance()));
      wager.setText("");
    });
    back.setOnClickListener(v -> {
      Intent intent2 = new Intent(getApplicationContext(), WalletActivity.class);
      intent2.putExtra(TWO_BALANCE, obj2.balance());
      startActivity(intent2);
    });

    info_btn.setOnClickListener(v -> {
      Intent intent3 = new Intent(getApplicationContext(), Info.class);
      startActivity(intent3);
    });
    if (savedInstanceState != null) {
      tBalance.setText(String.valueOf(savedInstanceState.getInt(TWO_BALANCE)));
      obj2.setBalance(savedInstanceState.getInt(TWO_BALANCE));
      die1.setText(String.valueOf(savedInstanceState.getInt("die1_value")));
      die2.setText(String.valueOf(savedInstanceState.getInt("die2_value")));
      die3.setText(String.valueOf(savedInstanceState.getInt("die3_value")));
      die4.setText(String.valueOf(savedInstanceState.getInt("die4_value")));
    }
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt(TWO_BALANCE, Integer.parseInt(tBalance.getText().toString()));
    outState.putInt("die1_value", Integer.parseInt(die1.getText().toString()));
    outState.putInt("die2_value", Integer.parseInt(die2.getText().toString()));
    outState.putInt("die3_value", Integer.parseInt(die3.getText().toString()));
    outState.putInt("die4_value", Integer.parseInt(die4.getText().toString()));
  }

  @Override
  public void onBackPressed(){
    Intent intent2 = new Intent(getApplicationContext(), WalletActivity.class);
    intent2.putExtra(TWO_BALANCE, obj2.balance());
    startActivity(intent2);
  }
}