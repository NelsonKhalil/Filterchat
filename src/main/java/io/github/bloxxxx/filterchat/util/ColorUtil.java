package io.github.bloxxxx.filterchat.util;

public class ColorUtil {
    private ColorUtil() {}

    public static int hex(String hex) {
        int len = hex.length();
        if (len == 8) {
            return (int) Long.parseLong(hex, 16);
        } else if (len == 6) {
            return (int) Long.parseLong("ff" + hex, 16);
        }
        return 0;
    }
}
