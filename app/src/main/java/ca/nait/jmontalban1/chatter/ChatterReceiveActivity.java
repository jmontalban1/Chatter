package ca.nait.jmontalban1.chatter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public class ChatterReceiveActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatter_receive);

        getFromChatter();
    }

    private void getFromChatter()
    {
        BufferedReader in = null;
        TextView textview = (TextView)findViewById(R.id.textview_receive_chatter);
        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI("http://www.youcode.ca/JSONServlet"));
            HttpResponse response= client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while((line = in.readLine()) != null)
            {
                textview.append(line + "\n");
            }
            in.close();
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu_item_send_chats:
            {
                Intent intent = new Intent(this,ChatterActivitySend.class);
                this.startActivity(intent);
                break;
            }
            case R.id.menu_item_view_list:
            {
                Intent intent = new Intent(this,ChatterListActivity.class);
                this.startActivity(intent);
            }
        }
        return true;
    }
}
