/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();
        earthquakes.add(new Earthquake("7.2", "San Francisco", "Jan 2018"));
        earthquakes.add(new Earthquake("5.4", "London", "Feb 2018"));
        earthquakes.add(new Earthquake("4.5", "Tokyo", "July 2016"));
        earthquakes.add(new Earthquake("6.7", "Mexico", "Feb 2015"));
        earthquakes.add(new Earthquake("9.5", "Moscow", "Oct 2014"));
        earthquakes.add(new Earthquake("10.4","Rio de Janeiro", "Sep 2027"));
        earthquakes.add(new Earthquake("8.9", "Paris", "Feb 2017"));

        ListView eqListView = (ListView) findViewById(R.id.list);
        EarthquakeAdapter eqAdapter = new EarthquakeAdapter(this, earthquakes);

        eqListView.setAdapter(eqAdapter);
    }

    private class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

        public EarthquakeAdapter(Context context, List<Earthquake> earthquakes){
            super(context, 0, earthquakes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View listItemView = convertView;

            if (listItemView == null){
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.earthquake_list_item, parent, false);
            }

            Earthquake currentEarthquake = getItem(position);

            TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
            TextView locationView = (TextView) listItemView.findViewById(R.id.location);
            TextView dateView = (TextView) listItemView.findViewById(R.id.date);

            magnitudeView.setText(currentEarthquake.getMagnitude());
            locationView.setText(currentEarthquake.getLocation());
            dateView.setText(currentEarthquake.getDate());

            return listItemView;
        }
    }
}
