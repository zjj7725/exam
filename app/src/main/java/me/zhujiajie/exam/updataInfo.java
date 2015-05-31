package me.zhujiajie.exam;


public class updataInfo {
    int versionCode;
    float versionName;
    String updateContent;
    String apkURL;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public float getVersionName() {
        return versionName;
    }

    public void setVersionName(float versionName) {
        this.versionName = versionName;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getApkURL() {
        return apkURL;
    }

    public void setApkURL(String apkURL) {
        this.apkURL = apkURL;
    }

    @Override
    public String toString() {
        return "updataInfo{" +
                "versionCode=" + versionCode +
                ", versionName=" + versionName +
                ", updateContent='" + updateContent + '\'' +
                ", apkURL='" + apkURL + '\'' +
                '}';
    }
}
