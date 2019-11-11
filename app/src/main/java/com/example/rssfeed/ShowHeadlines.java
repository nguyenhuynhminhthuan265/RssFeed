package com.example.rssfeed;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowHeadlines extends Activity {
    ArrayList<SingleItem> newsList = new ArrayList<>();
    ListView myListView;
    String urlAddress = "";
    String urlCaption = "";
    String channel = "";
    SingleItem selectedNewsItem;
    TextView txtTitle;
    String title;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTitle=findViewById(R.id.txtTitle);

        title="ITEMS IN CHANNEL ";

        myListView = this.findViewById(R.id.myListView);
// find out which intent is calling us
        Intent callingIntent = getIntent();
// grab data bundle holding selected url & caption sent to us
        Bundle myBundle = callingIntent.getExtras();
        urlAddress = myBundle.getString("urlAddress");
        urlCaption = myBundle.getString("urlCaption");
        channel = myBundle.getString("channel");
        txtTitle.setText(title + urlCaption + "-" + channel);
        // update app's top 'TitleBar' (eg. 'NPR - Business Wed April 09, 2014')
        this.setTitle("NPR - " + urlCaption + " \t" + MainActivity.niceDate());
// clicking on a row shows dialogBox with more info about selected item
        myListView = (ListView)this.findViewById(R.id.myListView);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View v,
                                    int index, long id) {
                selectedNewsItem = newsList.get(index);

                showNiceDialogBox(selectedNewsItem, getApplicationContext());
            }
        });
// get stories for the selected news option
        DownloadRssFeed downloader = new DownloadRssFeed(ShowHeadlines.this);
        downloader.execute(urlAddress, urlCaption);
    }//onCreate

    public void showNiceDialogBox(SingleItem selectedStoryItem,
                                  Context context){
// make a nice looking dialog box (story summary, btnClose, btnMore)
// CAUTION: (check)on occasions title and description are the same!
        String title = selectedStoryItem.getTitle();
        String description = selectedStoryItem.getDescription();
        if (title.toLowerCase().equals(description.toLowerCase())){
            description = "";
        }
        try {
//CAUTION: sometimes TITLE and DESCRIPTION include HTML markers
            final Uri storyLink = Uri.parse(selectedStoryItem.getLink());
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
            myBuilder
                    .setIcon(R.drawable.logo_vnex)
                    .setTitle(Html.fromHtml(urlCaption) )
                    .setMessage( title + "\n\n" + Html.fromHtml(description) + "\n" )
                    .setPositiveButton("Close", null)
                    .setNegativeButton("More", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichOne) {
                            Intent browser = new Intent(Intent.ACTION_VIEW, storyLink);
                            startActivity(browser);
                        }
                    })//setNegativeButton
                    .show();
        } catch (Exception e) {
            Log.e("Error DialogBox", e.getMessage() );
        }
    }//showNiceDialogBox
}
