package org.example;
import java.util.*;
import java.io.*;

import org.example.logs.Log;
import org.example.log_parser_factory.JsonLogParser;


public class Main {
    public static void main(String[] args) {

        String jsonLogFilePath = "ninja_events.json";
        ArrayList<Log> logs;

        System.out.println("------------------------------------------");
        System.out.println("JSON");
        System.out.println("------------------------------------------");

        JsonLogParser jsonLogParser = JsonLogParser.getInstance();

        try {
            logs = jsonLogParser.parseLogFile(jsonLogFilePath);
            for (Log log : logs) {
                System.out.println(log);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}