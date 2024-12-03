package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Taxi {
    private int id;
    private LocalDateTime departureTime;
    private int travelTime;
    private double distance;
    private double cost;
    private double tip;
    private String payingMethod;

    public Taxi(int id, LocalDateTime departureTime, int travelTime, double distance, double cost, double tip, String payingMethod) {
        this.id = id;
        this.departureTime = departureTime;
        this.travelTime = travelTime;
        this.distance = distance;
        this.cost = cost;
        this.tip = tip;
        this.payingMethod = payingMethod;
    }

    public Taxi(String[] myArray) {
        this.id = Integer.parseInt(myArray[0]);
        this.departureTime = LocalDateTime.parse(myArray[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.travelTime = Integer.parseInt(myArray[2]);
        this.distance = Double.parseDouble(myArray[3].replace(',','.'));
        this.cost = Double.parseDouble(myArray[4].replace(',','.'));;
        this.tip = Double.parseDouble(myArray[5].replace(',','.'));;
        this.payingMethod = myArray[6];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getTip() {
        return tip;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

    public String getPayingMethod() {
        return payingMethod;
    }

    public void setPayingMethod(String payingMethod) {
        this.payingMethod = payingMethod;
    }

    @Override
    public String toString() {
        return "Taxi{" +
                "id=" + id +
                ", departureTime=" + departureTime +
                ", travelTime=" + travelTime +
                ", distance=" + distance +
                ", cost=" + cost +
                ", tip=" + tip +
                ", payingMethod='" + payingMethod + '\'' +
                '}';
    }
}
