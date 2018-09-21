package ca.nait.jmontalban1.chatter;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.addPreferencesFromResource(R.xml.preferences);
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
            case R.id.menu_item_custom_list:
            {
                Intent intent = new Intent(this, ChatterCustomListActivity.class);
                this.startActivity(intent);
                break;
            }
        }
        return true;
    }




}
