package com.example.androidnangcaoca2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.androidnangcaoca2.dto.NewAdapter;
import com.example.androidnangcaoca2.dto.NewItem;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Bai5Activity extends AppCompatActivity {
    private ListView lvNews;
    public NewAdapter baseAdapter;
    public List<NewItem> news = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai5);
        news.add(new NewItem("a","a","a","a"));
        news.add(new NewItem("b","a","a","a"));
        baseAdapter = new NewAdapter(getBaseContext(), news);
        lvNews = findViewById(R.id.lvNews);
        lvNews.setAdapter(baseAdapter);
        new FeedService().execute("https://vnexpress.net/rss/tam-su.rss");
        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String link = news.get(position).getLink();
                Intent intent = new Intent(Bai5Activity.this, WebViewActivity.class);
                intent.putExtra("link", link);
                startActivity(intent);

            }
        });
    }

    public List<NewItem> feedRss(String urlString) throws XmlPullParserException, IOException {
//        XmlPullParser parser = Xml.newPullParser();
        ArrayList<NewItem> list = new ArrayList<>();
        InputStream stream = new URL(urlString).openConnection().getInputStream();
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(stream, "UTF-8");
        NewItem item = new NewItem();
        int eventType = parser.getEventType();
        String text = "";
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String name = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    System.out.println("START_TAG");
                    if(name.equalsIgnoreCase("item")) {
                        item = new NewItem();
                    }
                    break;
                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;
                case XmlPullParser.END_TAG:
                    if(name.equalsIgnoreCase("item")) {
                        list.add(item);
                    }
                    if(name.equalsIgnoreCase("title")) {
                        if(text !=null) {
                            item.setTitle(text);
                        }

                    } else if(name.equalsIgnoreCase("description")) {
                    if(text !=null) {
                        item.setDescription( text.replaceAll("^<.*./br>",""));
                    }

                }else if(name.equalsIgnoreCase("link")) {
                        if(text !=null) {
                            item.setLink(text);
                        }

                    }
                    break;
            }
        eventType = parser.next();
        }
        return list;
    }
    class FeedService extends AsyncTask<String, Integer, Integer> {

        @Override
        protected Integer doInBackground(String... strings) {
            try {
                news = feedRss(strings[0]);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            baseAdapter.updateBaseAdapter(news);
        }
    }
}
