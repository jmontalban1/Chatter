package ca.nait.jmontalban1.chatter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by JM's Property on 2018-10-02.
 */

public class SpinnerObjectAdapter extends ArrayAdapter
{
    private Context context;
    private ArrayList objects;
    public SpinnerObjectAdapter(Context context, int resourceId, ArrayList objects)
    {
        super(context, resourceId, objects);
        this.context = context;
        this.objects = objects;

    }
    public int getCount()
    {
        return objects.size(); //this will return the number of objects associated in the array
    }
    public Chat getItem(int index)
    {
        return (Chat)objects.get(index);
    }


    //function call when the list is collapsed
    @Override
    public View getView(int position, View row, ViewGroup parent)
    {
        TextView label = new TextView(context);
        label.setTextColor(Color.MAGENTA);

        Chat chat = getItem(position);
        label.setText(chat.getMessage());

        return label;
    }

    //function called getCount() times and populates each row of the list




    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {

        ChatterObjectActivity activity = (ChatterObjectActivity) context;
        LayoutInflater inflater = activity.getLayoutInflater();
        View spinnerRow = inflater.inflate(R.layout.spinner_object_row, null);

        TextView tvSender = (TextView) spinnerRow.findViewById(R.id.spinner_row_sender);
        TextView tvDate = (TextView) spinnerRow.findViewById(R.id.spinner_row_date);
        TextView tvMessage = (TextView) spinnerRow.findViewById(R.id.spinner_row_message);

        Chat chat = getItem(position);

        tvSender.setText(chat.getSender());
        tvDate.setText(chat.getDate());
        tvMessage.setText(chat.getMessage());

        return spinnerRow;
    }

    }

class MySpinnerListener implements AdapterView.OnItemSelectedListener
{
    static ChatterObjectActivity activity;

    public MySpinnerListener(Context context)
    {
        activity = (ChatterObjectActivity) context;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int index, long row)
    {
        Chat chat = (Chat) parent.getAdapter().getItem(index);

        EditText etSender = (EditText)activity.findViewById(R.id.edittext_sender);
        EditText etDate = (EditText)activity.findViewById(R.id.edittext_date);
        EditText etMessage = (EditText)activity.findViewById(R.id.edittext_message);

        etSender.setText(chat.getSender());
        etSender.setText(chat.getMessage());
        etDate.setText(chat.getDate());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }







}
