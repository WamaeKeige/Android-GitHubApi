package com.awake.restapi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import models.GitHubUser;
import rest.APIClient;
import rest.GitHubUserEndPoints;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
ImageView imgview;
TextView txtname, txtfollowers,txtlogin,txtemail, txtfollowing;
Button btn;
Bundle extras;
String newString;
Bitmap myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        imgview = findViewById(R.id.avatar);
        txtname = findViewById(R.id.txtviewname);
        txtfollowers = findViewById(R.id.txtviewfollowers);
        txtfollowing = findViewById(R.id.txtviewfollowing);
        txtemail = findViewById(R.id.txtviewemail);
        txtlogin = findViewById(R.id.txtviewlogin);

        btn = findViewById(R.id.btnrepo);

        extras = getIntent().getExtras();
        newString = extras.getString("STRING_I_NEED");

        System.out.println(newString);
        loadData();
    }

    public void getRepos(View view) {
        Intent intent = new Intent(UserActivity.this,Repositories.class);
        intent.putExtra("username", newString);
        startActivity(intent);

    }



    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }
            return null;
        }
    }


    public void loadData() {
        final GitHubUserEndPoints apiService =
                APIClient.getClient().create(GitHubUserEndPoints.class);

        Call<GitHubUser> call = apiService.getUser(newString);
        call.enqueue(new Callback<GitHubUser>() {

            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser>
                    response) {

                ImageDownloader task = new ImageDownloader();

                try {
                    myImage = task.execute(response.body().getAvatar()).get();

                } catch (Exception e) {

                    e.printStackTrace();

                }

                imgview.setImageBitmap(myImage);
                imgview.getLayoutParams().height=220;
                imgview.getLayoutParams().width=220;

                if(response.body().getName() == null){
                    txtname.setText("No name provided");
                } else {
                    txtname.setText("Username: " + response.body().getName());
                }

                txtfollowers.setText("Followers: " + response.body().getFollowers());
                txtfollowing.setText("Following: " + response.body().getFollowing());
                txtlogin.setText("name: " + response.body().getLogin());

                if(response.body().getEmail() == null){
                    txtemail.setText("No email provided");
                } else{
                    txtemail.setText("Email: " + response.body().getEmail());
                }



            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {
                System.out.println("Failed!" + t.toString());
            }
        });
    }
}
