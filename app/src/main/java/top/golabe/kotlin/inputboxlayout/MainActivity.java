package top.golabe.kotlin.inputboxlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import top.golabe.kotlin.library.OnCompleteListener;
import top.golabe.kotlin.library.InputBoxLayout;

public class MainActivity extends AppCompatActivity {
    private InputBoxLayout inputBoxLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputBoxLayout=findViewById(R.id.ibl_input);
        inputBoxLayout.setOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(String content) {
                Toast.makeText(MainActivity.this,content,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void reset(View view) {
        inputBoxLayout.reset();
    }
}
