package com.trankimphu.modernartui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar sbColor;
    private LinearLayout[] layouts = new LinearLayout[5];
    private int[] colors = new int[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layouts[0] = (LinearLayout) findViewById(R.id.color1);
        layouts[1] = (LinearLayout) findViewById(R.id.color2);
        layouts[2] = (LinearLayout) findViewById(R.id.color3);
        layouts[3] = (LinearLayout) findViewById(R.id.color4);
        layouts[4] = (LinearLayout) findViewById(R.id.color5);

        sbColor = (SeekBar) findViewById(R.id.sbColor);
        sbColor.setOnSeekBarChangeListener(this);

        for (int i = 0; i < layouts.length; i++) {
            colors[i] = ((ColorDrawable) layouts[i].getBackground()).getColor();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.modern_art, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int ID = menuItem.getItemId();
        if (ID == R.id.more_info) {

            DialogFragment dialogFragment = new MoreInfoDialog();
            dialogFragment.show(getFragmentManager(), "moreInfo");

            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progressChanged, boolean bo) {
        for (int i = 0; i < layouts.length; i++) {
            int color = colors[i];
            if (color != 0xffffffff && color != 0xff888888) {
                int invertColor = (0xFFFFFF - color) | 0xFF000000;
                int R = (color >> 16) & 0x000000FF;
                int G = (color >> 8) & 0x000000FF;
                int B = (color >> 0) & 0x000000FF;
                int invertR = (invertColor >> 16) & 0x000000FF;
                int invertG = (invertColor >> 8) & 0x000000FF;
                int invertB = (invertColor >> 0) & 0x000000FF;
                int newColor = Color.rgb(
                        (int) (R + ((invertR - R) * (progressChanged / 100f))),
                        (int) (G + ((invertG - G) * (progressChanged / 100f))),
                        (int) (B + ((invertB - B) * (progressChanged / 100f))));

                layouts[i].setBackgroundColor(newColor);
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}