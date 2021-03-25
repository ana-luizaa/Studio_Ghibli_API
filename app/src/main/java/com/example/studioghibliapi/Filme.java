package com.example.studioghibliapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Filme extends AppCompatActivity {

    TextView txtTitulo2, txtOriginal2, txtRomanizado2, txtDescricao2, txtDiretor2, txtAno2, txtTempo2, txtScore2;
    int id_To_Update = 0;

    Films films;
    dbHelper db = new dbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);
        getSupportActionBar().hide();

        txtTitulo2 = findViewById(R.id.txtTitulo2);
        txtOriginal2 = findViewById(R.id.txtOriginal2);
        txtRomanizado2 = findViewById(R.id.txtRomanizado2);
        txtDescricao2 = findViewById(R.id.txtDescricao2);
        txtDiretor2 = findViewById(R.id.txtDiretor2);
        txtAno2 = findViewById(R.id.txtAno2);
        txtTempo2 = findViewById(R.id.txtTempo2);
        txtScore2 = findViewById(R.id.txtScore2);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            int Value = extras.getInt("id");
            films = new Films();

            if(Value > 0){
                Cursor rs  = db.getData(Value);
                id_To_Update = Value;
                if(rs.moveToFirst()){
                    films.setTitle(rs.getString(rs.getColumnIndex(db.FILMS_COLUMN_TITLE)));
                    films.setOriginal(rs.getString(rs.getColumnIndex(db.FILMS_COLUMN_ORIGINAL_TITLE)));
                    films.setTitle_romanised(rs.getString(rs.getColumnIndex(db.FILMS_COLUMN_ORIGINAL_TITLE_ROMANISED)));
                    films.setDescription(rs.getString(rs.getColumnIndex(db.FILMS_COLUMN_DESCRIPTION)));
                    films.setDirector(rs.getString(rs.getColumnIndex(db.FILMS_COLUMN_DIRECTOR)));
                    films.setYear(rs.getString(rs.getColumnIndex(db.FILMS_COLUMN_RELEASE_DATE)));
                    films.setTime(rs.getString(rs.getColumnIndex(db.FILMS_COLUMN_RUNNING_TIME)));
                    films.setScore(rs.getString(rs.getColumnIndex(db.FILMS_COLUMN_RT_SCORE)));
                }
            } else{
                Toast.makeText(Filme.this,"Ops! Ocorreu um erro.", Toast.LENGTH_SHORT).show();
            }
            txtTitulo2.setText(films.getTitle());
            txtOriginal2.setText(films.getOriginal());
            txtRomanizado2.setText(films.getTitle_romanised());
            txtDescricao2.setText(films.getDescription());
            txtDiretor2.setText(films.getDirector());
            txtAno2.setText(films.getYear());
            txtTempo2.setText(films.getTime());
            txtScore2.setText(films.getScore());
        }
    }
    public void backButton(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}