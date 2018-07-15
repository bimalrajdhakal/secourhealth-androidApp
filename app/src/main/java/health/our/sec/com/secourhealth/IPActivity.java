package health.our.sec.com.secourhealth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IPActivity extends AppCompatActivity {
    EditText iptext;
    AppCompatButton submitBtn;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        iptext=(EditText)findViewById(R.id.ipaddresstext);
        submitBtn=(AppCompatButton) findViewById(R.id.submitbtn);

        iptext.setText(sh.getString("ip", "192.168.1.1"));

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip_address=iptext.getText().toString();

                if (!ip_address.equalsIgnoreCase("")){
                    // connection to server

                    SharedPreferences.Editor er=sh.edit();
                    er.putString("ip",ip_address);
                    er.commit();
                    // Intent for redirect to login page
                    Intent loginactivity =new Intent(getApplicationContext(),Login.class);
                    startActivity(loginactivity);
                }
                else{
                    // else condition
                    // message if not connected to server
                    Toast.makeText(getApplicationContext(),"Not Connected to Server",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
