package com.jkb.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jkb.rollinglayout.RollingLayout;
import com.jkb.rollinglayout.RollingLayoutAction;

public class MainActivity extends AppCompatActivity implements RollingLayoutAction.OnRollingChangedListener,
        RollingLayoutAction.OnRollingItemClickListener {

    private RollingAdapter rollingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rollingAdapter = new RollingAdapter(this);
        initUpDown();
        initDownUp();
        initLeftRight();
        initRightLeft();
    }

    private void initRightLeft() {
        RollingLayout rollingRightLeft = (RollingLayout) findViewById(R.id.rollingRightLeft);
        rollingRightLeft.setAdapter(rollingAdapter);
        rollingRightLeft.startRolling();

        rollingRightLeft.addOnRollingChangedListener(this);
        rollingRightLeft.setOnRollingItemClickListener(this);
    }

    private void initLeftRight() {
        RollingLayout rollingLeftRight = (RollingLayout) findViewById(R.id.rollingleftRight);
        rollingLeftRight.setAdapter(rollingAdapter);
        rollingLeftRight.startRolling();

        rollingLeftRight.addOnRollingChangedListener(this);
        rollingLeftRight.setOnRollingItemClickListener(this);
    }

    private void initDownUp() {
        RollingLayout rollingDownUp = (RollingLayout) findViewById(R.id.rollingdownUp);
        rollingDownUp.setAdapter(rollingAdapter);
        rollingDownUp.startRolling();

        rollingDownUp.addOnRollingChangedListener(this);
        rollingDownUp.setOnRollingItemClickListener(this);
    }

    private void initUpDown() {
        RollingLayout rollingUpDown = (RollingLayout) findViewById(R.id.rollingUpDown);
        rollingUpDown.setAdapter(rollingAdapter);
        rollingUpDown.startRolling();

        rollingUpDown.addOnRollingChangedListener(this);
        rollingUpDown.setOnRollingItemClickListener(this);
    }

    @Override
    public void onRollingChanged(View rollingLayout, int currentPosition, int sumPosition) {
        switch (rollingLayout.getId()) {
            case R.id.rollingUpDown:
                ((TextView) findViewById(R.id.am_rolling_upDown_count))
                        .setText("(" + currentPosition + "/" + sumPosition + ")");
                break;
            case R.id.rollingdownUp:
                ((TextView) findViewById(R.id.am_rolling_downUp_count))
                        .setText("(" + currentPosition + "/" + sumPosition + ")");
                break;
            case R.id.rollingleftRight:
                ((TextView) findViewById(R.id.am_rolling_leftRight_count))
                        .setText("(" + currentPosition + "/" + sumPosition + ")");
                break;
            case R.id.rollingRightLeft:
                ((TextView) findViewById(R.id.am_rolling_rightLeft_count))
                        .setText("(" + currentPosition + "/" + sumPosition + ")");
                break;
        }
    }

    @Override
    public void onRollingItemClick(View view, ViewGroup parent, int position) {
        switch (parent.getId()) {
            case R.id.rollingUpDown:
                Toast.makeText(MainActivity.this, "upDown rolling and position is:" + position,
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.rollingdownUp:
                Toast.makeText(MainActivity.this, "downUp rolling and position is:" + position,
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.rollingleftRight:
                Toast.makeText(MainActivity.this, "leftRight rolling and position is:" + position,
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.rollingRightLeft:
                Toast.makeText(MainActivity.this, "rightLeft rolling and position is:" + position,
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
