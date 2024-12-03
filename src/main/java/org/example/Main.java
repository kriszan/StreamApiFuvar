package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        ArrayList<Taxi> taxilist = readCsv();

        elsoFeladat(taxilist);
        masodikFeladat(taxilist, 6185);
        harmadikFeladat(taxilist);
        negyedikFeladat(taxilist);
        otodikFeladat(taxilist);
        hatodikFeladat(taxilist, 4261);
        hetedikFeladat(taxilist);
        negyedikFeladat(taxilist);
        kilencedikFeladat(taxilist);
        tizedikFeladat(taxilist);
        tizenegyedikFeladat(taxilist);
    }

    private static ArrayList<Taxi> readCsv() {
        ArrayList<Taxi> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("fuvar.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                Taxi values = new Taxi(line.split(";"));
                records.add(values);
            }
        } catch (Exception e) {
            System.out.printf(String.valueOf(e));
        }
        return records;
    }

    private static void elsoFeladat(List<Taxi> taxilist) {
        System.out.printf("Első feladat: %s" + System.lineSeparator(), taxilist.size());
    }


    public static void masodikFeladat(List<Taxi> input, int idToFind) {
        Map<Integer, Double> taxiCosts = input.stream()
                .collect(Collectors.groupingBy(Taxi::getId,
                        Collectors.summingDouble(Taxi::getCost)));

        taxiCosts.entrySet().stream()
                .filter(entry -> idToFind == entry.getKey())
                .forEach(entry -> System.out.println("Második feladat: " + entry.getKey() + " -- " + entry.getValue()));
    }

    public static void harmadikFeladat(List<Taxi> input) {
        System.out.printf("Harmadik feladat: " + input.stream().collect(Collectors.summingDouble(Taxi::getDistance)) + System.lineSeparator());
    }

    public static void negyedikFeladat(List<Taxi> taxis) {
        Taxi longestTravelTime = taxis.stream()
                .max(new Comparator<Taxi>() {
                    @Override
                    public int compare(Taxi first, Taxi second) {
                        return Integer.compare(first.getTravelTime(), second.getTravelTime());
                    }
                })
                .orElseThrow();

        System.out.printf("Negyedik feladat: %d%n", longestTravelTime.getTravelTime());
    }

    public static void otodikFeladat(List<Taxi> taxis) {
        System.out.printf("ötödik feladat: " + taxis.stream().max(new Comparator<Taxi>() {
            @Override
            public int compare(Taxi first, Taxi second) {
                return Double.compare(first.getTip() / first.getCost(), second.getTip() / second.getCost());
            }
        }).orElseThrow());
    }

    public static void hatodikFeladat(List<Taxi> taxis, int idToFind) {
        System.out.printf("Hatodik feladat: " + taxis.stream().collect(Collectors.groupingBy(Taxi::getId)).get(idToFind).stream().collect(Collectors.summingDouble(Taxi::getDistance)) * 1.6);
    }

    public static void hetedikFeladat(List<Taxi> taxis) {
        System.out.println("Hetedik feladat:");
        Stream<Taxi> myList = taxis.stream().filter(taxi -> taxi.getTravelTime() > 0 && taxi.getCost() > 0 && taxi.getDistance() == 0);
        System.out.printf("Invalid data count: %d%n", myList.count());
        //System.out.printf("Invalid data summarized time: "+ myList.collect(Collectors.summingDouble(Taxi::getTravelTime)));
        //System.out.printf("Invalid data incoming: "+ myList.collect(Collectors.summingDouble(taxi -> taxi.getCost() + taxi.getTip())));
    }

    public static void nyolcadikFeladat(List<Taxi> taxis, int taxiId) {
        if (taxis.stream()
                .filter(taxi -> taxi.getId() == taxiId)
                .count() > 0) {
            System.out.println("Nyolcadik feladat: " + "van");
        } else {
            System.out.println("Nyolcadik feladat: " + "nincs");
        }
    }

    public static void kilencedikFeladat(List<Taxi> taxis) {
        System.out.println("Kilencedik feladat: " + System.lineSeparator());
        taxis.stream().sorted(new Comparator<Taxi>() {
            @Override
            public int compare(Taxi first, Taxi second) {
                return Integer.compare(first.getTravelTime(), second.getTravelTime());
            }
        }).filter(entry -> entry.getTravelTime() != 0).limit(3).forEach(entry -> System.out.println(entry));
    }


    public static void tizedikFeladat(List<Taxi> taxis) {
        long count = taxis.stream()
                .filter(taxi -> taxi.getDepartureTime().isBefore(LocalDate.of(2016, 12, 24).atStartOfDay()))
                .count();
        System.out.println("Tizedik feladat: " + count);
    }

    public static void tizenegyedikFeladat(List<Taxi> taxis) {
        long transportsOnDate = taxis.stream()
                .filter(taxi -> taxi.getDepartureTime().isEqual(LocalDate.of(2016, 12, 24).atStartOfDay()))
                .count();
        long transportsWithTipOnDate = taxis.stream()
                .filter(taxi -> taxi.getDepartureTime().isEqual(LocalDate.of(2016, 12, 24).atStartOfDay()) && taxi.getTip() > 0)
                .count();
        System.out.println("Tizenegyedik feladat: " + transportsWithTipOnDate + " : " + (transportsOnDate - transportsWithTipOnDate));
    }
}