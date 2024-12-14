package com.wxy.springbackend.model;

import java.util.List;

public class TripSearch {
    private int pathId; // 路径 ID
    private List<String> stations; // 车站列表
    private List<String> arrivalTimeList; // 到达时间列表
    private double prices_A; // 票价
    private double prices_B; // 票价
    private String departStationId; // 始发站 ID
    private String arrivalStationId; // 到达站 ID
    private int aSeatsLeft; // A 类座位剩余数量
    private int bSeatsLeft; // B

    public TripSearch() {
    }


    public int getPathId() {
        return pathId;
    }

    public void setPathId(int pathId) {
        this.pathId = pathId;
    }

    public List<String> getStations() {
        return stations;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    public List<String> getArrivalTimeList() {
        return arrivalTimeList;
    }

    public void setArrivalTimeList(List<String> arrivalTimeList) {
        this.arrivalTimeList = arrivalTimeList;
    }

    public double getPrices_A() {
        return prices_A;
    }

    public void setPrices_A(double prices_A) {
        this.prices_A = prices_A;
    }

    public double getPrices_B() {
        return prices_B;
    }

    public void setPrices_B(double prices_B) {
        this.prices_B = prices_B;
    }

    public String getDepartStationId() {
        return departStationId;
    }

    public void setDepartStationId(String departStationId) {
        this.departStationId = departStationId;
    }

    public String getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(String arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public int getaSeatsLeft() {
        return aSeatsLeft;
    }

    public void setaSeatsLeft(int aSeatsLeft) {
        this.aSeatsLeft = aSeatsLeft;
    }

    public int getbSeatsLeft() {
        return bSeatsLeft;
    }

    public void setbSeatsLeft(int bSeatsLeft) {
        this.bSeatsLeft = bSeatsLeft;
    }


    @Override
    public String toString() {
        return "TripSearch{" +
                "pathId=" + pathId +
                ", stations=" + stations +
                ", arrivalTimeList=" + arrivalTimeList +
                ", prices_A=" + prices_A +
                ", prices_B=" + prices_B +
                ", departStationId='" + departStationId + '\'' +
                ", arrivalStationId='" + arrivalStationId + '\'' +
                ", aSeatsLeft=" + aSeatsLeft +
                ", bSeatsLeft=" + bSeatsLeft +
                '}';
    }
}
