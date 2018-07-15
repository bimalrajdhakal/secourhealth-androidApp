package health.our.sec.com.secourhealth;

import android.app.LauncherActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class AreaActivity extends AppCompatActivity {
    SharedPreferences sh ;
    String user_name;
////    private RecyclerView recyclerView;
////    private RecyclerView.Adapter adapter;
//
//    private List<LauncherActivity.ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

//        recyclerView=(RecyclerView)findViewById(R.id.areaRecyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        listItems=new ArrayList<>();
//        for(int i=0;i<10;i++){
//            ListItem listItem=new LauncherActivity.ListItem(
//                    "heading" +(i+1),
//                    "Lorem ipsum dummy text"
//            );
//
//        }
//
//        adapter =new AreaAdapter(listItems,this);
//        recyclerView.setAdapter(adapter);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        user_name=sh.getString("lid","");
        ur = "api/viewarea/?username=" + user_name + "";
        ur = ur.replace(" ", "%20");

        new Login.Loads().execute();
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

            }

        }
    }
}
