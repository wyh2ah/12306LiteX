package com.wxy.javafxfrontend;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchController {
    private final HttpClient httpClient = HttpClient.newHttpClient();

    @FXML private Button backButton;
    @FXML private Label routeLabel;
    @FXML private Label dateLabel;
    @FXML private Button prevDayButton;
    @FXML private Button nextDayButton;
    @FXML private VBox ticketContainer;

    // 上一个页面传来的数据
    private int userId;
    private String username;
    private String departStation;
    private String arrivalStation;
    private String selectedDate;

    private List<TripSearch> ticketData;

    public void initialize() {}

    public void setData(int userId, String username, String departStation, String arrivalStation, String date) throws IOException, InterruptedException {
        this.userId = userId;
        this.username = username;
        this.departStation = departStation;
        this.arrivalStation = arrivalStation;
        this.selectedDate = date;
        routeLabel.setText(departStation + " → " + arrivalStation);
        dateLabel.setText(date);

        String requestBody = "depart_station=" + departStation + "&arrival_station=" + arrivalStation + "&datetime=" + selectedDate;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8088/api/search"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, TripSearch.class);
        ticketData = mapper.readValue(response.body(), listType);
        renderTickets();
    }

    @FXML
    private void handlePrevDay() throws IOException, InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate dateTime = LocalDate.parse(this.selectedDate, formatter).minusDays(1);

        String requestBody = "depart_station=" + departStation + "&arrival_station=" + arrivalStation + "&datetime=" + dateTime.toString();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8088/api/search"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, TripSearch.class);
        ticketData = mapper.readValue(response.body(), listType);
        renderTickets();

        this.selectedDate = dateTime.toString();
        dateLabel.setText(this.selectedDate);
    }

    @FXML
    private void handleNextDay() throws IOException, InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate dateTime = LocalDate.parse(this.selectedDate, formatter).plusDays(1);

        String requestBody = "depart_station=" + departStation + "&arrival_station=" + arrivalStation + "&datetime=" + dateTime.toString();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8088/api/search"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, TripSearch.class);
        ticketData = mapper.readValue(response.body(), listType);

        renderTickets();

        this.selectedDate = dateTime.toString();
        dateLabel.setText(this.selectedDate);
    }

    private void renderTickets() {
        ticketContainer.getChildren().clear();
        if (ticketData != null && !ticketData.isEmpty()) {
            for (TripSearch item : ticketData) {
                VBox ticketItem = createTicketItem(item);
                ticketContainer.getChildren().add(ticketItem);
            }
        }
    }


    private VBox createTicketItem(TripSearch item) {
        VBox ticketBox = new VBox();
        ticketBox.getStyleClass().add("ticket-item");
        ticketBox.setSpacing(10);

        // 数据解析
        String depart = item.getStations().getFirst();
        String arrival = item.getStations().getLast();
        List<String> stations = item.getStations();
        List<String> arrivalTimeList = item.getArrivalTimeList();
        String departTime = arrivalTimeList.getFirst();
        String arriveTime = arrivalTimeList.getLast();
        int stopCount = stations.size() - 2;

        Label headerLabel = new Label(depart + " → " + arrival + " on Train: " + item.getTrain_name());
        headerLabel.getStyleClass().add("ticket-header");

        Label timeInfo = new Label("Departure Time: " + departTime + "   Arrival Time: " + arriveTime + "   Stop Station Number: " + stopCount);
        timeInfo.getStyleClass().add("ticket-subinfo");

        StringBuilder path = new StringBuilder();
        for (String station : stations) {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime trainTime = LocalDateTime.parse(arrivalTimeList.get(stations.indexOf(station)), inputFormatter);
            String timeString = trainTime.format(outputFormatter);
            path.append(station).append(" ").append(timeString).append(" → ");
        }
        path = new StringBuilder(path.substring(0, path.length() - 3));
        Label stopInfo = new Label(path.toString());
        stopInfo.getStyleClass().add("ticket-stopinfo");

        // 展开座位信息按钮
        Label expandButton = new Label("Check Seat Occupancy");
        expandButton.getStyleClass().add("ticket-expand-button");

        // 座位信息面板（默认隐藏）
        VBox seatInfoPanel = createSeatInfoPanel(item);
        seatInfoPanel.setVisible(false);
        seatInfoPanel.setManaged(false);

        expandButton.setOnMouseClicked(e -> {
            boolean currentlyVisible = seatInfoPanel.isVisible();
            seatInfoPanel.setVisible(!currentlyVisible);
            seatInfoPanel.setManaged(!currentlyVisible);
            expandButton.setText(currentlyVisible ? "Unfold to Check Seat Occupancy" : "Fold to Hide Seat Occupancy");
        });

        ticketBox.getChildren().addAll(headerLabel, timeInfo, stopInfo, expandButton, seatInfoPanel);

        return ticketBox;
    }

    private VBox createSeatInfoPanel(TripSearch item) {
        VBox seatPanel = new VBox(10);
        seatPanel.getStyleClass().add("seat-info-panel");

        double priceA = item.getPrices_A();
        double priceB = item.getPrices_B();
        double priceC = item.getPrices_C();
        int aLeft = item.getaSeatsLeft();
        int bLeft = item.getbSeatsLeft();
        int cLeft = item.getcSeatsLeft();

        // A座位
        HBox aRow = createSeatRow("A Class", aLeft, priceA);
        // B座位
        HBox bRow = createSeatRow("B Class", bLeft, priceB);
        // C座位
        HBox cRow = createSeatRow("C Class", cLeft, priceC);

        seatPanel.getChildren().addAll(aRow, bRow, cRow);

        return seatPanel;
    }

    private HBox createSeatRow(String seatType, int left, double price) {
        HBox row = new HBox(10);
        row.getStyleClass().add("seat-type-row");

        Label seatTypeLabel = new Label("Seat Type: " + seatType);
        seatTypeLabel.getStyleClass().add("seat-type-label");

        DecimalFormat df = new DecimalFormat("#.00"); // 格式化为两位小数
        String formattedValue = df.format(price);
        Label priceLabel = new Label("Price: " + formattedValue);
        priceLabel.getStyleClass().add("seat-available-label");

        Label availableLabel = new Label("Tickets Left: " + left);
        availableLabel.getStyleClass().add("seat-available-label");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        row.getChildren().addAll(seatTypeLabel, priceLabel, availableLabel, spacer);

        if (left > 0) {
            Button bookBtn = new Button("Book Now");
            bookBtn.getStyleClass().add("book-button");
            bookBtn.setOnAction(e -> handleBookTicket(seatType));

            row.getChildren().add(bookBtn);
        }

        return row;
    }

    @FXML
    private void handleBackButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Scene scene = new Scene(loader.load(), 1920, 1080);
        HomeController controller = loader.getController();
        controller.setParameters(userId, username);

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



    private void handleBookTicket(String seatType) {
        // 订票逻辑的回调
    }

    public static class TripSearch {
        private int pathId;
        private List<String> stations;
        private List<String> arrivalTimeList;
        private double prices_A;
        private double prices_B;
        private double prices_C;
        private String departStationId;
        private String arrivalStationId;
        private int aSeatsLeft;
        private int bSeatsLeft;
        private int cSeatsLeft;
        private String train_name;

        public String getTrain_name() {
            return train_name;
        }

        public void setTrain_name(String train_name) {
            this.train_name = train_name;
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

        public double getPrices_C() {
            return prices_C;
        }

        public void setPrices_C(double prices_C) {
            this.prices_C = prices_C;
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

        public int getcSeatsLeft() {
            return cSeatsLeft;
        }

        public void setcSeatsLeft(int cSeatsLeft) {
            this.cSeatsLeft = cSeatsLeft;
        }
    }

}
