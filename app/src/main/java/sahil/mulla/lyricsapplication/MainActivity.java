package sahil.mulla.lyricsapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText edtArtistName, edtArtistSong;
    private Button btnGetLyrics;
    private TextView txtLyrics;

    String url;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtArtistName = findViewById(R.id.edtArtistName);
        edtArtistSong = findViewById(R.id.edtArtistSongTitle);
        btnGetLyrics = findViewById(R.id.btnGetLyrics);
        txtLyrics = findViewById(R.id.txtLyrics);




        btnGetLyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Tapped", Toast.LENGTH_LONG).show();

                url = "https://api.lyrics.ovh/v1/" + edtArtistName.getText().toString() + "/" + edtArtistSong.getText().toString();
                url.replaceAll(" " ,"%20");

                requestQueue = Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest
                        jsonObjectRequest
                        = new JsonObjectRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener() {
                            @Override
                            public void onResponse(Object response) {


                                try {
                                    JSONObject jsonObject = new JSONObject(response.toString());
                                    txtLyrics.setText(jsonObject.getString("lyrics"));


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                            }
                        });
                requestQueue.add(jsonObjectRequest);
            }
        });



    }
}
