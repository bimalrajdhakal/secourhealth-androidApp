package health.our.sec.com.secourhealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class navigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
   // Dashboard dashHome;
    private CardView areaCard,surveyCard,uploadCard,registerCard,reportsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

          //  dashHome=new Dashboard();
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera actionnavigationActivity
        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
