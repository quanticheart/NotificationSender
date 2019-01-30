package quanticheart.com.notificationsender;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import quanticheart.com.packagelist.PackageList;
import quanticheart.com.packagelist.PackageListAdapter;
import quanticheart.com.packagelist.PackageUtils;
import quanticheart.com.testnotification.DialogUtils;
import quanticheart.com.testnotification.NotificationSend;
import quanticheart.com.testnotification.Utils;

public class MainActivity extends AppCompatActivity implements PackageListAdapter.PackageInfoListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageListAdapter.setPackageInfoListener(MainActivity.this);

        PackageList.init(MainActivity.this, (RecyclerView) findViewById(R.id.recycler), false);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationSend.sendNotification(
                        getApplicationContext(),
                        "Teste Movida",
                        "Notifcação de teste para o app do cliente",
                        "br.com.appfactory.movida");
            }
        });
    }

    @Override
    public void information(PackageUtils.PInfo info) {

        DialogUtils.openDialog(
                MainActivity.this,
                info.getAppname(),
                "Notification Send to " + info.getAppname(),
                info.getPname());

//
    }
}
