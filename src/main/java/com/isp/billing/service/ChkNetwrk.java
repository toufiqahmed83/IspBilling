package com.isp.billing.service;

import com.isp.billing.model.sms.SmsConfg;
import com.isp.billing.model.sms.SmsYmlInterFace;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Created by Toufiq on 7/27/2019.
 */
public class ChkNetwrk {

    public boolean ChqCon(String cUrl)
    {
        boolean Result = true;
        try
        {
            URL url = new URL(cUrl);

            URLConnection connection = url.openConnection();
            connection.connect();

            System.out.println("Internet Connected");
        }
        catch (Exception e)
        {
            System.out.println("Sorry, No Internet Connection");
            Result = false;
        }
        return Result;
    }

    public boolean isDatabaseOnline(String address, int port)
    {
        boolean result;
        try
        {
            Socket socket = new Socket(address, port);
            socket.close();
            System.out.println("DB Up");
            result = true;
        }
        catch (IOException e)
        {
//      boolean result;
            result = false;
            System.out.println("DB Down");
        }
        return result;
    }


    public boolean isInterfaceOnline(String address)
    {
        boolean result;
        try
        {
            Socket socket = new Socket(address,80);
            socket.close();
            System.out.println("DB Up");
            result = true;
        }
        catch (IOException e)
        {
//      boolean result;
            result = false;
            System.out.println("DB Down");
        }
        return result;
    }

    public static void testLocalCon()
            throws UnknownHostException, IOException
    {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        try
        {
            while (interfaces.hasMoreElements())
            {
                NetworkInterface nic = (NetworkInterface)interfaces.nextElement();

                System.out.print("Interface Name : [" + nic.getName() + ' ' + nic.getDisplayName() + "]");
                System.out.println(", Is connected : [" + nic.isUp() + "]");
            }
        }
        catch (IOException e)
        {
            System.out.print(e);
        }
    }

    public boolean interFcae()
            throws SocketException
    {
        boolean netStatus = false;
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {
            netStatus = displayInterfaceInformation(netint);
        }
        return netStatus;
    }

    public void givenName_whenReturnsNetworkInterface_thenCorrect()
            throws SocketException
    {
        NetworkInterface nif = NetworkInterface.getByName("lo");

        System.out.println("nif" + nif.getHardwareAddress());
    }

    static boolean displayInterfaceInformation(NetworkInterface netint)
            throws SocketException
    {
        boolean status = false;
        System.out.printf("Display name: %s\n", new Object[] { netint.getDisplayName() });
        System.out.printf("Name: %s\n", new Object[] { netint.getName() });
        System.out.printf("Connection: %s\n", new Object[] { Boolean.valueOf(netint.isUp()) });
        if (netint.isUp() != true) {
            status = false;
        }
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            System.out.printf("InetAddress: %s\n", new Object[] { inetAddress });
        }
        System.out.printf("\n", new Object[0]);
        return status;
    }

    public String getDataFromYml(int Param)
            throws IOException
    {
        String output = null;

        Yaml yaml = new Yaml();

        InputStream in = ChkNetwrk.class.getResourceAsStream("/DBCredentials.yml");Throwable localThrowable3 = null;
        try
        {
            SmsYmlInterFace person = (SmsYmlInterFace)yaml.loadAs(in, SmsYmlInterFace.class);
            System.out.println(person.getURL());
            if (Param == 1) {
                output = person.getURL();
            } else {
                output = person.getPort();
            }
        }
        catch (Throwable localThrowable1)
        {
            localThrowable3 = localThrowable1;throw localThrowable1;
        }
        finally
        {
            if (in != null) {
                if (localThrowable3 != null) {
                    try
                    {
                        in.close();
                    }
                    catch (Throwable localThrowable2)
                    {
                        localThrowable3.addSuppressed(localThrowable2);
                    }
                } else {
                    in.close();
                }
            }
        }
        return output;
    }
}
