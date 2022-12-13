package com.example.doodler;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import java.util.ArrayList;


public class Controller extends AppCompatActivity {

    private DoodlerView doodlerView;
    private View mColorPreview;
    private int colorSelected = Color.RED;

    private ArrayList<Integer> colors = new ArrayList<>();
    private int colorIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        doodlerView = (DoodlerView) findViewById(R.id.doodlerview);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        doodlerView.init(metrics);

        Button clearButton = (Button) findViewById(R.id.button_clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clear(v);
            }
        });

        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.BLACK);

        SeekBar sizeBar = (SeekBar) findViewById(R.id.seekbar);
        sizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                changeSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Button colorButton = (Button) findViewById(R.id.button_color);
        colorButton.setBackgroundColor(colors.get(colorIndex));
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorIndex++;
                if (colorIndex >= colors.size()) {
                    colorIndex = 0;
                }
                changeColor(v, colors.get(colorIndex));
            }
        });

        Button undoButton = (Button) findViewById(R.id.button_undo);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doodlerView.undo();
            }
        });

        Button redoButton = (Button) findViewById(R.id.button_redo);
        redoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doodlerView.redo();
            }
        });

    }

    public void changeSize(int width) {
        doodlerView.changeStrokeWidth(width);
    }

    public void clear(View view) {
        doodlerView.clear();
    }

    public void changeColor(View v, int color) {
        v.setBackgroundColor(color);
        doodlerView.changeColor(color);
    }
}
