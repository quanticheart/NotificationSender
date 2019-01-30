package quanticheart.com.packagelist;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PackageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<PackageUtils.PInfo> arrayList;
    private Activity activity;
    LayoutInflater mInflater;

    PackageListAdapter(Activity activity, RecyclerView recyclerView, boolean getSysPackages) {
        this.activity = activity;
        arrayList = PackageUtils.getInstalledApps(activity, getSysPackages);
        mInflater = activity.getSystemService(LayoutInflater.class);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PackagesHolder(mInflater.inflate(R.layout.list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof PackagesHolder) {
            PackagesHolder holder = (PackagesHolder) viewHolder;
            holder.bindView(arrayList.get(i));
            ((PackagesHolder) viewHolder).view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.information(arrayList.get(i));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class PackagesHolder extends RecyclerView.ViewHolder {
        View view;

        PackagesHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }

        private void bindView(PackageUtils.PInfo info) {
            ((ImageView) view.findViewById(R.id.icon)).setImageDrawable(info.getIcon());
            ((TextView) view.findViewById(R.id.line1)).setText(info.getAppname());
            ((TextView) view.findViewById(R.id.line2)).setText(info.getPname());
            ((TextView) view.findViewById(R.id.line3)).setText(info.getVersionName());
        }
    }

    //==============================================================================================
    //
    // ** Interface
    //
    //==============================================================================================

    public static void setPackageInfoListener(PackageInfoListener mListener) {
        listener = mListener;
    }

    private static PackageInfoListener listener;

    public interface PackageInfoListener {
        void information(PackageUtils.PInfo info);
    }
}

