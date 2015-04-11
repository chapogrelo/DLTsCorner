package com.chapo.stupidapps.dltscorner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by chapo on 09/04/2015.
 */
public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
    private List<DrawerRow> drawerRows;

    private int selectedView = 0;
    private Context context;
    private  int rowColor;
    private int selectedBGColor;
    private int unSelectedBGColor;
    private int accentColor;

    public class ViewHolder extends RecyclerView.ViewHolder{
        int holderid;

        TextView sectionTitleView;
        ImageView sectionIconView;

        ImageView profilePicView;
        TextView nickNameView;
        TextView mojoView;

        public ViewHolder(Context inContext,View itemView, int viewType) {
            super(itemView);
            switch(viewType){
                case(DrawerRow.HEADER):
                    nickNameView = (TextView) itemView.findViewById(R.id.name);
                    mojoView = (TextView) itemView.findViewById(R.id.mojo);
                    profilePicView = (ImageView) itemView.findViewById(R.id.circleView);
                    holderid = DrawerRow.HEADER;
                    itemView.setClickable(false);
                    break;
                case(DrawerRow.SECTION):
                    sectionTitleView = (TextView) itemView.findViewById(R.id.rowText);
                    sectionIconView = (ImageView) itemView.findViewById(R.id.rowIcon);
                    holderid = DrawerRow.SECTION;
                    break;
                case(DrawerRow.DIVIDER):
                    holderid = DrawerRow.DIVIDER;
                    itemView.setClickable(false);
                    break;
                default:
                    break;
            }
            context = inContext;
            itemView.setTag(R.id.VIEW_TYPE,holderid);
        }
    }

    public DrawerAdapter(Context inContext, List<DrawerRow> inDrawerRows) {
        this(inContext,inDrawerRows,1);
    }
    public DrawerAdapter(Context inContext, List<DrawerRow> inDrawerRows, int selectedPos) {
        drawerRows = inDrawerRows;
        context = inContext;
        rowColor = context.getResources().getColor(R.color.drawer_row_color);
        selectedBGColor= context.getResources().getColor(R.color.drawer_selected_row_bg);
        unSelectedBGColor = context.getResources().getColor(R.color.drawer_row_bg);
        accentColor = context.getResources().getColor(R.color.accent_color);
        selectedView = selectedPos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch(viewType){
            case(DrawerRow.HEADER):
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false);

                ViewHolder vhHeader = new ViewHolder(context,v,viewType);

                return vhHeader;

            case(DrawerRow.SECTION):
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_row,parent,false);

                ViewHolder vhItem = new ViewHolder(context,v,viewType);

                return vhItem;

            case (DrawerRow.DIVIDER):
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.divider,parent,false);
                ViewHolder vhDivider = new ViewHolder(context,v,viewType);

                return vhDivider;
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch(holder.holderid){
            case(DrawerRow.HEADER):
                DrawerRowHeader header = (DrawerRowHeader)drawerRows.get(position);
                holder.nickNameView.setText(header.getNickName());
                holder.mojoView.setText(header.getMojo());
                holder.profilePicView.setImageResource(header.getProfilePic());
                break;

            case(DrawerRow.SECTION):
                int itemColor;
                if(selectedView == position){
                    itemColor = accentColor;
                }else {
                    itemColor = rowColor;
                }
                DrawerRowSection row = (DrawerRowSection)drawerRows.get(position);
                Drawable icon = row.icon;
                icon.mutate().setColorFilter(itemColor, PorterDuff.Mode.MULTIPLY);
                holder.sectionIconView.setImageDrawable(icon);
                holder.sectionTitleView.setText(row.getTitle());
                holder.sectionTitleView.setTextColor(itemColor);
                break;

            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return drawerRows.size();
    }

    @Override
    public int getItemViewType(int position) {
        return drawerRows.get(position).getType();
    }

    public boolean setSelected(View view, int position){
        selectedView = position;
        view.setActivated(!view.isActivated());
        notifyDataSetChanged();
        return true;
    }

    public int getSelectedView() {
        return selectedView;
    }
}
