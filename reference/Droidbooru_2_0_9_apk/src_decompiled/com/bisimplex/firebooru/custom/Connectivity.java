package com.bisimplex.firebooru.custom;
public class Connectivity {

    public Connectivity()
    {
        return;
    }

    public static android.net.NetworkInfo getNetworkInfo(android.content.Context p1)
    {
        return ((android.net.ConnectivityManager) p1.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static boolean isConnected(android.content.Context p0)
    {
        int v0_1 = com.bisimplex.firebooru.custom.Connectivity.getNetworkInfo(p0);
        if ((v0_1 == 0) || (!v0_1.isConnected())) {
            return 0;
        } else {
            return 1;
        }
    }

    public static boolean isConnectedFast(android.content.Context p1)
    {
        int v1_1 = com.bisimplex.firebooru.custom.Connectivity.getNetworkInfo(p1);
        if ((v1_1 == 0) || ((!v1_1.isConnected()) || (!com.bisimplex.firebooru.custom.Connectivity.isConnectionFast(v1_1.getType(), v1_1.getSubtype())))) {
            return 0;
        } else {
            return 1;
        }
    }

    public static boolean isConnectedMobile(android.content.Context p1)
    {
        int v1_1 = com.bisimplex.firebooru.custom.Connectivity.getNetworkInfo(p1);
        if ((v1_1 == 0) || ((!v1_1.isConnected()) || (v1_1.getType() != 0))) {
            return 0;
        } else {
            return 1;
        }
    }

    public static boolean isConnectedWifi(android.content.Context p1)
    {
        int v1_1 = com.bisimplex.firebooru.custom.Connectivity.getNetworkInfo(p1);
        if ((v1_1 == 0) || ((!v1_1.isConnected()) || (v1_1.getType() != 1))) {
            return 0;
        } else {
            return 1;
        }
    }

    public static boolean isConnectionFast(int p2, int p3)
    {
        if (p2 != 1) {
            if (p2 != 0) {
                return 0;
            } else {
                switch (p3) {
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        return 1;
                    case 4:
                    case 7:
                    case 11:
                    default:
                        return 0;
                }
            }
        } else {
            return 1;
        }
    }
}
