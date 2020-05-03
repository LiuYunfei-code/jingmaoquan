package com.project.jingmaoquan.util;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondPhotoUtil {
    public static List<String> getPhotos(String photos) {
        if (StringUtils.isEmpty(photos)){
            return null;
        }
        String searchString = photos;
        String regexString = "<img.*?>";
        Pattern datePattern = Pattern.compile(regexString);
        Matcher dateMatcher = datePattern.matcher(searchString);
        int beEndIndex = 0;
        List<String> photoList=new ArrayList<>();
        while(dateMatcher.find()) {
            String subString = dateMatcher.group();
            photoList.add(subString);
            int subIndex = searchString.indexOf(subString);

            int subLength = subString.length();

            beEndIndex = subIndex + subLength + beEndIndex;
            searchString = photos.substring(beEndIndex);
            dateMatcher = datePattern.matcher(searchString);
        }
        return photoList;
    }
}
