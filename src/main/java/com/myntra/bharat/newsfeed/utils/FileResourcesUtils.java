package com.myntra.bharat.newsfeed.utils;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

/*
 * Developer : Bharat Verma
 * Created : Tue 17-Nov-2020 03:21 PM
 **/
public class FileResourcesUtils {

    public static void main(String[] args) throws IOException, URISyntaxException {

        FileResourcesUtils app = new FileResourcesUtils();

        //String fileName = "database.properties";
        String fileName = "json/file1.json";

        System.out.println("getResourceAsStream : " + fileName);
        InputStream is = app.getFileFromResourceAsStream(fileName);
        app.printInputStream(is);

        System.out.println("getResource : " + fileName);
        File file = app.getFileFromResource(fileName);
        System.out.println(app.getFileAsString(file));

    }

    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
    public File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }

    }

    private void printInputStream(InputStream is) {

        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // print a file
    public String getFileAsString(File file) {
        List<String> lines;
        StringBuilder response = new StringBuilder();
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            lines.forEach(response::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    public String getFileContents (String filename ) throws URISyntaxException {
        return getFileAsString(getFileFromResource(filename));
    }

    public List<String> getFileAsList(String filename) throws URISyntaxException, IOException {
        return Files.readAllLines(getFileFromResource(filename).toPath(), StandardCharsets.UTF_8);
    }
}
