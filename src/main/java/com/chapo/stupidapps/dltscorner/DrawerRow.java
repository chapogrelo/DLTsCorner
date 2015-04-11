package com.chapo.stupidapps.dltscorner;

/**
 * classe représentant un ligne quelquonque dans l'adapteur du navigation drawer
 */
public abstract class DrawerRow {
    public static final int HEADER = 0;
    public static final int SECTION = 1;
    public static final int DIVIDER = 2;


    DrawerRow(){
    }

    abstract int getType();

}
