package com.kalela.InnovexSolutions.util;

import java.util.ArrayList;
import java.util.List;

public class Colors {
    private static class StartColors {
        static int color1 = 0xFFB3E9C7;
        static int color2 = 0xFFC2F8CB;
        static int color3 = 0xFF1282A2;
        static int color4 = 0xFF0B2027;
        static int color5 = 0xFFFF92C2;
    }

    private static class StopColors {
        static int color1 = 0xFF0A1128;
        static int color2 = 0xFF40798C;
        static int color3 = 0xFF595758;
        static int color4 = 0xFF22031F;
        static int color5 = 0xFFCC76A1;
    }

    private static class ReportColors {
        static int color1 = 0xFF8367C7;
        static int color2 = 0xFFCFD7C7;
        static int color3 = 0xFFF6F1D1;
        static int color4 = 0xFFFA9500;
        static int color5 = 0xFFBABD8D;
    }

    public static List<Integer> startColors = new ArrayList<Integer>() {{
        add(StartColors.color1);
        add(StartColors.color2);
        add(StartColors.color3);
        add(StartColors.color4);
        add(StartColors.color5);
    }};

    public static List<Integer> stopColors = new ArrayList<Integer>() {{
        add(StopColors.color1);
        add(StopColors.color2);
        add(StopColors.color3);
        add(StopColors.color4);
        add(StopColors.color5);
    }};

    public static List<Integer> reportColors = new ArrayList<Integer>() {{
        add(ReportColors.color1);
        add(ReportColors.color2);
        add(ReportColors.color3);
        add(ReportColors.color4);
        add(ReportColors.color5);
    }};
}
