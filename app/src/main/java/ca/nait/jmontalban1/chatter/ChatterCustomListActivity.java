package ca.nait.jmontalban1.chatter;


import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatterCustomListActivity extends ListActivity {

    ArrayList<HashMap<String, String>> chatter = new ArrayList<HashMap<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceStated)
    {


        super.onCreate(savedInstanceStated);
        setContentView(R.layout.activity_chatter_custom_list);
        displayChatter();
    }

    private void displayChatter()
    {
        String[] keys = new String[] {"SENDER" , "MESSAGE", "DATE"};
        int [] ids = new int []{R.id.custom_row_sender, R.id.custom_row_message, R.id.custom_row_date};

        populateList();

        SimpleAdapter adapter = new SimpleAdapter(this, chatter, R.layout.custom_list_row,keys,ids);

        this.setListAdapter(adapter);
    }
    private void populateList()
    {
        BufferedReader in = null;
        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI("http://www.youcode.ca/JitterServlet"));
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";

            while((line = in.readLine()) != null)
            {
                HashMap<String, String> temp = new HashMap<String, String>();

                temp.put("SENDER", line);
                line = in.readLine();
                temp.put("MESSAGE", line);
                line = in.readLine();
                temp.put("DATE", line);
                line = in.readLine();


            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error" + e, Toast.LENGTH_LONG).show();
        }
    }
}
