package ca.nait.jmontalban1.chatter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;


public class ChatterObjectActivity extends AppCompatActivity
{
    private Spinner objectSpinner;
    private SpinnerObjectAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatter_object);

        ArrayList chatter = new ArrayList();

        spinnerAdapter = new SpinnerObjectAdapter(this, android.R.layout.simple_spinner_item, chatter);

        objectSpinner = (Spinner) findViewById(R.id.spinner_chatter_objects);
        objectSpinner.setAdapter(spinnerAdapter);
        objectSpinner.setOnItemSelectedListener(new MySpinnerListener(this));

    }
    private void populateArray(ArrayList chatter)
    {
        BufferedReader in = null;
    try
    {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet();
        request.setURI (new URI("http://www.youcode.ca/JitterServlet"));
        HttpResponse response = client.execute(request);
        in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while((line = in.readLine())!=null);

            Chat temp = new Chat();

            temp.setSender(line);

            line= in.readLine();
            temp.setMessage(line);

            line = in.readLine();
            temp.setDate(line);



    }
    catch (Exception e)
    {
        Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();

    }
    }
}

