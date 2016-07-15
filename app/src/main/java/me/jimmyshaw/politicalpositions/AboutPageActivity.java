package me.jimmyshaw.politicalpositions;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Element versionElement = new Element();

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionElement.setTitle("Version " + packageInfo.versionName);
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

//        Element advertisingElement = new Element();
//        advertisingElement.setTitle("Advertise with us");

        View aboutPage = new AboutPage(this).isRTL(false)
                .setImage(R.drawable.img_company_logo)
                .setDescription("US Election 2016 Issues\nby\nJimmy Shaw")
                .addItem(versionElement)
                .addGroup("Connect with us")
                .addGitHub("jimxshaw")
                .addWebsite("http://www.guildsa.org")
                .create();

        setContentView(aboutPage);
    }
}
