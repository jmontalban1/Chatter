package ca.nait.jmontalban1.chatter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import static android.R.attr.x;

public class SimpleSpinnerActivity extends AppCompatActivity
{

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_spinner);

        Spinner gradeSpinner = (Spinner)findViewById(R.id.spinner_grades);

        //SimplerSpinnerListener listener = new SimplerSpinnerListener(); this line is exactly the same as the one at the bootom "grade spinner.set..."


        gradeSpinner.setOnItemSelectedListener(new SimplerSpinnerListener());
    }
    class SimplerSpinnerListener implements AdapterView.OnItemSelectedListener
    {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {

            TextView textView_grade =
                    (TextView)SimpleSpinnerActivity.this.findViewById(R.id.textView_grade);
            String grade = parent.getResources().getStringArray(R.array.grade_names)[position];
            textView_grade.setText("You earned a " + grade);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent)
        {

        }
    }
}


