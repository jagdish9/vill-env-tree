package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenerateChecksum {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
     //   String cksum = getsha1(new File("C:\\softwares\\csv generated files\\10-March-2021\\package.json"));
      //  String cksum = getsha1(new File("C:\\softwares\\OSCS_9\\oscskb-crawler\\oscskbcrawler\\OSCSKB_PYTHON_COMPONENTS\\fancyqt-1.0.1.tar\\dist\\FancyQt-1.0.1\\FancyQt-1.0.1\\FancyQt.egg-info\\PKG-INFO"));
      //  String cksum = getsha1(new File("C:\\Users\\jkaiwari\\Downloads\\dom4j-1.6.1.jar"));
        String cksum = getsha1OfString("this is a test");
        System.out.println(cksum);
    }

    private static boolean checkDownloadable(){
        return false;
    }

    public static String getsha1(final File file) throws NoSuchAlgorithmException, IOException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
      //  MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        FileInputStream fis = new FileInputStream(file);
        FileChannel channel = fis.getChannel();
        int length = (int) channel.size();
        StringBuilder sb = new StringBuilder("");
        MappedByteBuffer buffer = null;
        try {
            buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
            for (int p = 0; p < length; p++) {
                messageDigest.update(buffer.get(p));
            }
            byte[] mdbytes = messageDigest.digest();
            for (byte mdbyte : mdbytes) {
                sb.append(Integer.toString((mdbyte & 0xff) + 0x100, 16).substring(1));
            }
        } finally {
            fis.close();
            channel.close();
        }

        return sb.toString();
    }

    public static String getsha1OfString(String textContent) throws NoSuchAlgorithmException, IOException {
        String sha1 = "";

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(textContent.getBytes("utf8"));
            sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e){
            e.printStackTrace();
        }

      /*  System.out.println("The sha1 of \""+ textContent + "\" is:");
        System.out.println(sha1);
        System.out.println();*/

        return sha1;
    }
}
