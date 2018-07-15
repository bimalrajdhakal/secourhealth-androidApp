package health.our.sec.com.secourhealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

/**
 * Created by bimal on 5/3/18.
 */

public class Dashboard extends AppCompatActivity implements View.OnClickListener{
    private CardView areaCard,surveyCard,uploadCard,registerCard,reportsCard;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_navigation);
        // defining Cards of Dashboard
        areaCard=(CardView)findViewById(R.id.area_Card);
        surveyCard=(CardView)findViewById(R.id.survey_Card);
        uploadCard=(CardView)findViewById(R.id.upload_Card);
        registerCard=(CardView)findViewById(R.id.register_Card);
        reportsCard=(CardView)findViewById(R.id.reports_Card);
        // Add Click Listener to the card
        areaCard.setOnClickListener(this);
        surveyCard.setOnClickListener(this);
        uploadCard.setOnClickListener(this);
        registerCard.setOnClickListener(this);
        reportsCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        Intent i;
        switch (v.getId()) {

            case R.id.area_Card:
                i = new Intent(this, AreaActivity.class);
                startActivity(i);
                break;
            case R.id.survey_Card:
                i=new Intent(this,SurveyActivity.class);
                startActivity(i);
                break;
            case R.id.upload_Card:
                i=new Intent(this,UploadActivity.class);
                startActivity(i);
                break;
            case R.id.register_Card:
                i= new Intent(this,RegisterActivity.class);
                startActivity(i);
                break;
            case R.id.reports_Card:
                i=new Intent(this,ReportsActivity.class);
                startActivity(i);
                break;

            default:break;


        }

    }
}
