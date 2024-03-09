package com.android.macromate.data;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String listToString(List<String> listStr) {
        StringBuilder builder = new StringBuilder(listStr.size() * 7);

        for (String s: listStr) {
            builder.append(s).append(", ");
        }

        return builder.substring(0, builder.length() - 2);   // strip last comma
    }

    @TypeConverter
    public static List<String> stringToList(String s) {
        return new ArrayList<>(Arrays.asList(s.split(", ")));
    }

    @TypeConverter
    public static Map<String, String> fromString(String value) {
        Type mapType = new TypeToken<Map<String, String>>() {
        }.getType();
        return new Gson().fromJson(value, mapType);
    }

    @TypeConverter
    public static String fromStringMap(Map<String, String> map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}

