package com.chapo.stupidapps.dltscorner;

/**
 * classe représentant le header du drawer dans l'adapter
 */
public class DrawerRowHeader extends DrawerRow {

    String nickName ;
    String mojo ;
    int profilePic;

    public DrawerRowHeader(String nickName, String mojo, int profilePic) {
        super();
        this.nickName = nickName;
        this.mojo = mojo;
        this.profilePic = profilePic;
    }

    @Override
    int getType() {
        return HEADER;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMojo() {
        return mojo;
    }

    public void setMojo(String mojo) {
        this.mojo = mojo;
    }

    public int getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
    }

}
