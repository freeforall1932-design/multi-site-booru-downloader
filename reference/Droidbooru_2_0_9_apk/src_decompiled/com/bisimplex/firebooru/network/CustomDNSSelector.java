package com.bisimplex.firebooru.network;
public class CustomDNSSelector implements okhttp3.Dns {

    public CustomDNSSelector()
    {
        return;
    }

    public java.util.List lookup(String p4)
    {
        java.util.Iterator v4_1 = okhttp3.Dns.SYSTEM.lookup(p4);
        java.util.ArrayList v0_2 = new java.util.ArrayList();
        java.util.Iterator v4_2 = v4_1.iterator();
        while (v4_2.hasNext()) {
            java.net.InetAddress v1_1 = ((java.net.InetAddress) v4_2.next());
            if ((v1_1 instanceof java.net.Inet4Address)) {
                v0_2.add(v1_1);
            }
        }
        return v0_2;
    }
}
