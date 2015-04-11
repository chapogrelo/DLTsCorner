package com.chapo.stupidapps.dltscorner;

import android.graphics.drawable.Drawable;

/**
 * classe représentant le un ligne de section du drawer dans l'adapter
 */
public class DrawerRowSection extends DrawerRow {
    Drawable icon;
    int title;

    public DrawerRowSection(Drawable icon, int title) {
        super();
        this.icon = icon;
        this.title = title;
    }

    @Override
    int getType() {
        return SECTION;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }
}
