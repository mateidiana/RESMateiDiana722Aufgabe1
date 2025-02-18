package org.example.log_parser_factory;

import com.google.gson.Gson;

import org.example.logs.Log;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JsonLogParser {
    private static JsonLogParser instance;

    private JsonLogParser() {
        // Private constructor to enforce Singleton pattern
    }

    public static JsonLogParser getInstance() {
        if (instance == null) {
            instance = new JsonLogParser();
        }
        return instance;
    }

    public ArrayList<Log> parseLogFile(String path) throws IOException {
        // Read the JSON file
        FileReader fileReader = new FileReader(path);

        // Parse JSON using Gson
        Gson gson = new Gson();
        Log[] logEntries = gson.fromJson(fileReader, Log[].class);

        ArrayList<Log> logs = new ArrayList<>(List.of(logEntries));

        fileReader.close();

        return logs;
    }
}

