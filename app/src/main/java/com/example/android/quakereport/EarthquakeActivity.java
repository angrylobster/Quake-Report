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
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();
        ListView eqListView = (ListView) findViewById(R.id.list);
        EarthquakeAdapter eqAdapter = new EarthquakeAdapter(this, earthquakes);

        eqListView.setAdapter(eqAdapter);
    }

    private class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

        private static final String LOCATION_SEPARATOR = " of ";
        String primaryLocation;
        String locationOffset;

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
            String originalLocation = currentEarthquake.getLocation();

            if (originalLocation.contains(LOCATION_SEPARATOR)) {
                String[] parts = originalLocation.split(LOCATION_SEPARATOR);
                locationOffset = parts[0] + LOCATION_SEPARATOR;
                primaryLocation = parts[1];
            } else {
                locationOffset = getContext().getString(R.string.near_the);
                primaryLocation = originalLocation;
            }

            Date dateObject = new Date(currentEarthquake.getUnixEpochTime());
            String formattedDate = formatDate(dateObject);

            TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
            TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
            TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
            TextView dateView = (TextView) listItemView.findViewById(R.id.date);
            TextView timeView = (TextView) listItemView.findViewById(R.id.time);

            magnitudeView.setText(currentEarthquake.getMagnitude());
            primaryLocationView.setText(primaryLocation);
            locationOffsetView.setText(locationOffset);
            dateView.setText(formattedDate);
            timeView.setText(formatTime(dateObject));

            return listItemView;
        }
    }

    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
