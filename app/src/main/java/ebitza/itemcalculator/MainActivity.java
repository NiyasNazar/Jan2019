package ebitza.itemcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyListFragment1  myListFragment1=new MyListFragment1();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment1,myListFragment1).commit();
    }

    @Override
    public void onClick(View view) {

        }

    }
