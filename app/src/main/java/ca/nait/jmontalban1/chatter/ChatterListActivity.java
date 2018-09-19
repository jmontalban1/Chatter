package ca.nait.jmontalban1.chatter;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;

public class ChatterListActivity extends ListActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getFromChatter();
    }
    public void getFromChatter()
    {
        BufferedReader in = null;
        ArrayList chats = new ArrayList();
        ArrayList recieve = new ArrayList();


        try
        {

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI("http://www.youcode.ca/JSONServlet"));
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            String NL = System.getProperty("line.separator");

            while((line = in.readLine()) != null)
            {
                chats.add(line);
            }
            in.close();

            ArrayAdapter<ArrayList> adapter = new ArrayAdapter<ArrayList>
            (this, android.R.layout.simple_list_item_1, chats);
            setListAdapter(adapter);


        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }

        try
        {

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI("http://www.youcode.ca/JSONServlet"));
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            String NL = System.getProperty("line.separator");

            while((line = in.readLine()) != null)
            {
                recieve.add(line);
            }
            in.close();

            ArrayAdapter<ArrayList> adapter = new ArrayAdapter<ArrayList>
                    (this, android.R.layout.simple_list_item_2, recieve);
            setListAdapter(adapter);
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }


    }
}

