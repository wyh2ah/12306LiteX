package com.wxy.javafxfrontend;

public class Settings {
    private static int res_width = 1600;
    private static int res_height = 900;

    public static int get_x() {
        return res_width;
    }

    public void set_x(int res_width) {
        Settings.res_width = res_width;
    }

    public static int get_y() {
        return res_height;
    }

    public void set_y(int res_height) {
        Settings.res_height = res_height;
    }
}
