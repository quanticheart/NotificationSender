package quanticheart.com.packagelist;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class PackageList {

    public static void init(Activity activity, RecyclerView recyclerView, boolean getSysPackages) {
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        PackageListAdapter adapter = new PackageListAdapter(activity, recyclerView, getSysPackages);
        recyclerView.setAdapter(adapter);
    }

}
