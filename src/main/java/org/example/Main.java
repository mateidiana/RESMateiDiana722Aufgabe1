package org.example;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

import org.example.logs.Log;
import org.example.log_parser_factory.JsonLogParser;


public class Main {
    public static void main(String[] args) {

        String jsonLogFilePath = "ninja_events.json";
        ArrayList<Log> logs=new ArrayList<>();

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

        //b.

//        System.out.println("Enter a value for points:");
//        Scanner scanner = new Scanner(System.in);
//        Double input = Double.parseDouble(scanner.nextLine());
//        printNinjasWithPointsGreaterThan(input,logs);

        //c.
        System.out.println("\n\n\n");
//        sortByDatesFilteredByStage(logs);

        //d.
        //writeStagesToFile(logs,"gesamtzahl.txt");
    }

    public static void printNinjasWithPointsGreaterThan(double points, ArrayList<Log> logs){
        for (Log log:logs){
            if(log.getPunkte()>=points)
                System.out.println(log.getName());
        }
    }

    public static void sortByDatesFilteredByStage(ArrayList<Log> logs){
        Map<LocalDate,Log> sortedLogs=new LinkedHashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Log log:logs){
            if (log.getStufe().equals("Jonin")){
                LocalDate date = LocalDate.parse(log.getDatum(), formatter);
                sortedLogs.put(date,log);
            }

        }

        //Descending

        List<Map.Entry<LocalDate, Log>> entryList = new ArrayList<>(sortedLogs.entrySet());
        entryList.sort(Map.Entry.<LocalDate, Log>comparingByKey().reversed());

        // Clear the original map and reinsert entries in sorted order
        sortedLogs.clear();
        for (Map.Entry<LocalDate, Log> entry : entryList) {
            sortedLogs.put(entry.getKey(), entry.getValue());
        }

        System.out.println("\nAfter sorting (descending by date):");
        sortedLogs.forEach((date, event) -> System.out.println(date + " -> " + event));

    }

    public static void writeStagesToFile(ArrayList<Log> logs, String filePath){
        Map<String, Integer> stageCount = new HashMap<>();
        for (Log log : logs) {
            stageCount.put(log.getStufe(), stageCount.getOrDefault(log.getStufe(), 0) + 1);
        }

        Map<String, Integer> sortedMap = stageCount.entrySet().stream()
                .sorted((e1, e2) -> {
                    int countCompare = Integer.compare(e2.getValue(), e1.getValue());  // Descending by count
                    if (countCompare != 0) {
                        return countCompare;
                    } else {
                        return e1.getKey().compareTo(e2.getKey());  // Alphabetically if counts are the same
                    }
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new // Preserve the sorted order
                ));


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
                writer.write(entry.getKey()+"%"+entry.getValue());
                writer.newLine();
            }
            System.out.println("Scores written to file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

}