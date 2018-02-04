package codility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class PhotoProblem {
    public static String solution(String S) {
        // write your code in Java SE 8
        String[] lines = S.split("\\r?\\n");
        Map<String, List<Photo>> locationMap = new HashMap<>();
        List<Photo> finalResult = new ArrayList<>();
        for (String s : lines) {
            String[] metaData = s.split(",");
            String[] photoName = metaData[0].trim().split("\\.");
            Photo photo = new Photo(photoName[0].trim(), photoName[1].trim(), metaData[1].trim(), metaData[2].trim());
            finalResult.add(photo);
            List<Photo> photoList = new ArrayList<>();
            if (locationMap.containsKey(metaData[1].trim())) {
                photoList = locationMap.get(metaData[1].trim());
            }
            photoList.add(photo);
            locationMap.put(metaData[1].trim(), photoList);
        }

        for (Map.Entry<String, List<Photo>> entry : locationMap.entrySet()) {
            List<Photo> photoList = entry.getValue();
            Collections.sort(photoList, Comparator.comparing(Photo::getPhotoTime));
            int length = photoList.size();
            int counter = 1;
            int totalDigits = String.valueOf(length).length();
            for (Photo photo : photoList) {
                photo.setName(counter, totalDigits);
                counter++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Photo photo : finalResult) {
            sb.append(photo.getOutput()).append(System.getProperty("line.separator"));
        }

        return sb.toString();
    }

    static class Photo {
        String name;
        String extension;
        String location;
        LocalDateTime photoTime;

        LocalDateTime getPhotoTime() {
            return photoTime;
        }

        Photo(String name, String extension, String location, String inputDate) {
            this.name = name;
            this.extension = extension;
            this.location = location;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            this.photoTime = LocalDateTime.parse(inputDate, formatter);
        }

        void setName(int number, int totalDigits) {
            name = location + String.format("%0{0}d", totalDigits, number);
        }

        String getOutput() {
            return name + "." + extension;
        }

    }

    public static void main(String[] args) {
        String s = "photo.jpg, Warsaw, 2013-09-05 14:08:15\njohn.png, London, 2015-06-20 15:13:22\nmyFriends.png, Warsaw, 2013-09-05 14:07:13\nEiffel.jpg, Paris, 2015-07-23 08:03:02\npisatower.jpg, Paris, 2015-07-22 23:59:59\nBOB.jpg, London, 2015-08-05 00:02:03\nnotredame.png, Paris, 2015-09-01 12:00:00\nme.jpg, Warsaw, 2013-09-06 15:40:22\na.png, Warsaw, 2016-02-13 13:33:50\nb.jpg, Warsaw, 2016-01-02 15:12:22\nc.jpg, Warsaw, 2016-01-02 14:34:30\nd.jpg, Warsaw, 2016-01-02 15:15:01\ne.png, Warsaw, 2016-01-02 09:49:09\nf.png, Warsaw, 2016-01-02 10:55:32\ng.jpg, Warsaw, 2016-02-29 22:13:11";
        System.out.println(solution(s));
    }
}