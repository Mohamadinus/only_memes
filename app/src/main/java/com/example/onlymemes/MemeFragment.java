package com.example.onlymemes;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.badge.BadgeUtils;
import com.iammert.library.readablebottombar.ReadableBottomBar;

import org.json.JSONException;
import org.json.JSONObject;


public class MemeFragment extends Fragment {
    ImageView imageView;
    ProgressBar progressBar;
    String currentImageurl;
    Button btn1,btn2;
    public String URL = "https://meme-api.herokuapp.com/gimme/meme";
    public MemeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meme, container, false);
        imageView = view.findViewById(R.id.imageView);
        progressBar = view.findViewById(R.id.progress);
        btn1 = view.findViewById(R.id.button3);
        btn2 = view.findViewById(R.id.button4);
        ExtractMeme();
        nextMeme();
        shareMeme();
        return view;
    }

    private void ExtractMeme() {
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue;
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    currentImageurl = response.getString("url");
                    Glide.with(MemeFragment.this).load(currentImageurl).into(imageView);
                    progressBar.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    public void nextMeme() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExtractMeme();
            }
        });
    }

    public void shareMeme() {
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                Intent chooser = Intent.createChooser(intent,"Share this meme using...");
                intent.putExtra(Intent.EXTRA_TEXT,"Hey!, checkout this cool meme "+currentImageurl);
                startActivity(intent);
            }
        });

    }
}