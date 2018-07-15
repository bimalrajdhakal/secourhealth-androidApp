package health.our.sec.com.secourhealth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    EditText userEmail,password;
    Button loginBtn;
    String username,pass;
    String ur,ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEmail=(EditText)findViewById(R.id.ed_email);
        password=(EditText)findViewById(R.id.ed_password);
        loginBtn=(Button)findViewById(R.id.btnLogin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=userEmail.getText().toString();
                pass=password.getText().toString();

                int flg=0;
                if(username.equalsIgnoreCase("")){
                    userEmail.setError("This field is required");
                    flg++;
                }
                if(pass.equalsIgnoreCase("")){
                    password.setError("This field is required");
                    flg++;
                }
                if(pass.length()<=4){
                    password.setError("Password criteria not match !!!");
                    flg++;
                }

                if(flg==0) {

                    ur = "api/login/?username=" + username + "&password=" + pass + "";
                    ur = ur.replace(" ", "%20");

                    new Loads().execute();
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

                JSONArray ar = new JSONArray(jsn.get("result").toString());
                String tmp = ar.getString(0);

                JSONObject jsnn = new JSONObject(tmp);
                ss = jsnn.getString("user_name");

                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor ed= sh.edit();
                ed.putString("lid", ss);
                ed.commit();
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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(ss.equalsIgnoreCase("Failed"))
            {
                Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_LONG).show();
            }
            else {

                Intent navactivity = new Intent(getApplicationContext(), navigationActivity.class);
                startActivity(navactivity);
            }
        }
    }

}
