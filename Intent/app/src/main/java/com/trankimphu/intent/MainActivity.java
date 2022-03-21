package com.trankimphu.intent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static public final int CODE_HELLO = 1;
    private EditText edtUsername = null, edtPassword = null;
    private Button btnLogin = null;

    static public final int REQUESE_IMAGE_CAPTURE = 2;
    private Button btnCamera1, btnCamera2;
    private ImageView imgView;

    private LinearLayout imgLayout;

//    float db = getApplicationContext().getResources().getDisplayMetrics().density;
//    int left = (int) (10 * db + 0.5f);
//    int right = (int) (10 * db + 0.5f);
//    int top = (int) (20 * db + 0.5f);
//    int bot = (int) (20 * db + 0.5f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login();

//        imgView = (ImageView) findViewById(R.id.imgView);

        imgLayout = (LinearLayout) findViewById(R.id.imgLayout);

        btnCamera1 = (Button) findViewById(R.id.btnCamera1);
        btnCamera1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TakePicture();
            }
        });

        btnCamera2 = (Button) findViewById(R.id.btnCamera2);
        btnCamera2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCameraActivityForResult();
            }
        });

    }

    private void TakePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Intent choooser = Intent.createChooser(takePictureIntent, "Chọn app để gọi");
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(choooser, REQUESE_IMAGE_CAPTURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODE_HELLO) {
            if (resultCode == RESULT_OK) {
                String s = data.getStringExtra("txt");
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == REQUESE_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            imgView.setImageBitmap(imgBitmap);
        }
    }

    ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Bundle extras = data.getExtras();
                        Bitmap imgBitmap = (Bitmap) extras.get("data");
                        imgView.setImageBitmap(imgBitmap);
                        imgView.setPadding(10, 10, 50, 10);
//                        imgView.setMaxWidth(300);
//                        imgView.setMaxHeight(300);
                        imgLayout.addView(imgView);
//                        imgLayout.setPadding(100,100,100,100);
                    }
                }
            });

    private void openCameraActivityForResult() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraActivityResultLauncher.launch(intent);
        imgView = new ImageView(this);
    }

    private void Login() {
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPassword(edtUsername.getText().toString(), edtPassword.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivityForResult(intent, CODE_HELLO);
                }
                else {
                    Uri page = Uri.parse("http://thongtindaotao.sgu.edu.vn/");
                    Intent intWeb = new Intent(Intent.ACTION_VIEW, page);
                    Intent choooser = Intent.createChooser(intWeb, "Chọn app để gọi");
                    startActivity(choooser);
                }
            }
        });
    }

    private boolean checkPassword(String user, String pass) {
        return user.equals("hello") && pass.equals("123");
    }
}