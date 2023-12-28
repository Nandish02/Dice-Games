package androidsamples.java.dicegames;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WalletActivity extends AppCompatActivity {
  private TextView mTxtBalance;
  private Button mBtnDie;
  static final String TOTAL_BALANCE = "TOTAL_BALANCE";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wallet);
    mTxtBalance = findViewById(R.id.txt_balance);
    mBtnDie = findViewById(R.id.btn_die);
    Button mBtntwoormore = findViewById(R.id.btn_launch_twoormore);
    Die mDie = new Die6();
    WalletViewModel obj = new WalletViewModel();
    obj.setDie(mDie);
    obj.setWinValue(6);
    obj.setIncrement(5);
    mBtnDie.setText("0");

    int new_bal=getIntent().getIntExtra(TwoOrMoreActivity.TWO_BALANCE,0);
    obj.setBalance(new_bal);
    mTxtBalance.setText(String.valueOf(new_bal));

    mBtnDie.setOnClickListener(v -> {
      obj.rollDie();
      mBtnDie.setText(String.valueOf(obj.dieValue()));
      mTxtBalance.setText(String.valueOf(obj.balance()));
    });

    mBtntwoormore.setOnClickListener(v -> {
      Intent intent = new Intent(getApplicationContext(),TwoOrMoreActivity.class);
      intent.putExtra(TOTAL_BALANCE, obj.balance());
      startActivity(intent);
    });
    if (savedInstanceState!=null)
    {
      int bal_temp= savedInstanceState.getInt("total");
      obj.setBalance(bal_temp);
      mTxtBalance.setText(String.valueOf(bal_temp));
      mBtnDie.setText(String.valueOf(savedInstanceState.getInt("die_value")));
    }
  }
  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt("total", Integer.parseInt(mTxtBalance.getText().toString()));
    outState.putInt("die_value",Integer.parseInt(mBtnDie.getText().toString()));
  }

  @Override
  public void onBackPressed(){

    moveTaskToBack(true);
  }
}