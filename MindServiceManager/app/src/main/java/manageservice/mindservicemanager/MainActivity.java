package manageservice.mindservicemanager;
import Common.RestClient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.DomainCombiner;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText email,pass, Dom;
    String username, password, Domain;
    String csrftoken, token;
    Intent i = new Intent(MainActivity.this , Home.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=(Button)findViewById(R.id.login);

        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.password);
        Dom=(EditText)findViewById(R.id.Domain);

        username=email.getText().toString();
        password=pass.getText().toString();
        Domain=Dom.getText().toString();
        System.out.println("enter");
        i.putExtra("key", Domain);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestClient obj = new RestClient();
                System.out.println("obj created");

                startActivity(i);

                Map<String, String> in = new HashMap<String, String>();
                in.put("username", username);
                in.put("password", password);
                /*csrftoken= obj.getcsrftoken("http://192.168.50.228");
                token= obj.gettoken("http://192.168.50.228" , in);
                obj.postrequest("http://192.168.50.228", "",in);*/
                csrftoken= obj.getcsrftoken(Domain);
                token= obj.gettoken(Domain , in);
                obj.postrequest(Domain, "",in);

                System.out.print("welcome");
            }
        });
    }

}


