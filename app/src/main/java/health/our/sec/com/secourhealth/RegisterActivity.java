package health.our.sec.com.secourhealth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    EditText fullName,address,email,mobile,company;
    AppCompatButton register_btn;
    String full_name,address_txt,email_id,mob_no,company_name;
    String ur="",ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullName=(EditText)findViewById(R.id.full_name);
        address=(EditText)findViewById(R.id.address);
        email=(EditText)findViewById(R.id.email);
        mobile=(EditText)findViewById(R.id.mobile);
        company=(EditText)findViewById(R.id.company);
        register_btn=(AppCompatButton)findViewById(R.id.register_btn);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                full_name=fullName.getText().toString();
                address_txt=address.getText().toString();
                email_id=email.getText().toString();
                mob_no=mobile.getText().toString();
                company_name=company.getText().toString();

                int flg = 0;

                if (full_name.equalsIgnoreCase("")){
                    fullName.setError("Please fill this field");
                    flg++;
                }

                if (address_txt.equalsIgnoreCase(""))
                {
                    address.setError("Please fill this field");
                    flg++;
                }
                if(mob_no.equalsIgnoreCase("")){
                    mobile.setError("Please fill this field");
                    flg++;
                }
                if(mob_no.length()!=10){
                    mobile.setError("Mobile No. Should be 10 digits");
                    flg++;
                }
                if(company_name.equalsIgnoreCase("")){
                    company.setError("Please fill this field");
                    flg++;
                }

                if (flg == 0) {

                    ur = "api/register/?full_name=" + full_name + "&address=" + address_txt
                            + "&email_id=" + email_id + "&mobile_no=" + mob_no + "&company=" + company_name + "";
                    ur = ur.replace(" ", "%20");

                    new RegisterActivity.Loads().execute();
                }

            }
        });
    }
    private String getData()
    {
        JsonAct js=new JsonAct(getApplicationContext());
        String result=js.setJsonVal(ur);
        Log.v("pppppppppppp", result);
        ss = "";
        try
        {
            JSONObject jsn = new JSONObject(result);
            if (jsn.get("status").equals("Success") ) {
                ss="Success";
            } else {
                ss = "Failed";
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.v("eeeeeeeeeee", e.toString());
        }
        return ss;
    }

    private class Loads extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... strings) {
            String res = getData();
            return res;
        }

        @Override
        protected void onPostExecute(String ss) {
            super.onPostExecute(ss);
            if(ss.equalsIgnoreCase("Success"))
            {
                Toast.makeText(getApplicationContext(),"Person Registered Successfully!!",Toast.LENGTH_LONG).show();
                Intent regintent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(regintent);
            }
            else {

                Intent regintent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(regintent);
            }
        }
    }

}
