package com.techxform.anywherematrimony;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.techxform.anywherematrimony.data.NavListModel;

import java.util.ArrayList;
import java.util.List;



public class NavListAdapter extends RecyclerView.Adapter<NavListAdapter.ViewHolder> {


    Activity activity1;
    DrawerLayout drawerLayout;
    private List<NavListModel> contactItems = new ArrayList<>();


    public NavListAdapter(List<NavListModel> contactItems, Activity activity, DrawerLayout drawerLayout) {
        this.contactItems = contactItems;
        this.activity1 = activity;
        this.drawerLayout = drawerLayout;

    }

    public List<NavListModel> getStudentist() {
        return contactItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_menu_listitem, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        NavListModel navListModel = getStudentist().get(position);
        holder.nav_imgvw.setImageResource(navListModel.getImageName());
        holder.nav_txtvw.setText(navListModel.getNavName());

        holder.full_layout.setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            /*if (position == 0) {
                Intent intent = new Intent(activity1, ProfilePage.class);
                activity1.startActivity(intent);
            } else if (position == 1) {
                Intent intent = new Intent(activity1, MyBookingsPage.class);
                activity1.startActivity(intent);
            } else if (position == 2) {
                Intent intent = new Intent(activity1, NotificationsPage.class);
                activity1.startActivity(intent);
            } else if (position == 3) {
                Intent intent = new Intent(activity1, OgoCardDetailsPage.class);
                activity1.startActivity(intent);
            } else if (position == 4) {
                Intent intent = new Intent(activity1, WebViewPage.class);
                intent.putExtra("weburl", "http://ogoentertainer.com/ogoclub.php");
                activity1.startActivity(intent);
            } else if (position == 5) {
                Intent intent = new Intent(activity1, WebViewPage.class);
                intent.putExtra("weburl", "http://ogoentertainer.com/ogocard.php");
                activity1.startActivity(intent);
            } else if (position == 6) {
                Intent intent = new Intent(activity1, ListYourBusinessPage.class);
                activity1.startActivity(intent);
            } else if (position == 7) {
                Intent intent = new Intent(activity1, PolicyPage.class);
//                intent.putExtra("weburl","http://ogoentertainer.com/privacy.php");
                activity1.startActivity(intent);
            } else if (position == 8) {
                Intent intent = new Intent(activity1, TermsConditions.class);
//                intent.putExtra("weburl","http://ogoentertainer.com/termsandconditions.php");
                activity1.startActivity(intent);
            } else if (position == 10) {
                logout();
            }*/
        });
    }


    @Override
    public int getItemCount() {
        return contactItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout full_layout;
        TextView nav_txtvw;
        ImageView nav_imgvw;

        public ViewHolder(View itemView) {
            super(itemView);

            nav_imgvw = (ImageView) itemView.findViewById(R.id.nav_imgvw);
            nav_txtvw = (TextView) itemView.findViewById(R.id.nav_txtvw);
            full_layout = (RelativeLayout) itemView.findViewById(R.id.full_layout);
        }

    }
}