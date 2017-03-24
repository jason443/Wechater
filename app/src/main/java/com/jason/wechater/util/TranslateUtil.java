package com.jason.wechater.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by jason on 2017/3/21.
 */

public class TranslateUtil {

     public static String convertStreamToString(InputStream is) {
                /*
 3           * To convert the InputStream to String we use the BufferedReader.readLine()
 4           * method. We iterate until the BufferedReader return null which means
 5           * there's no more data to read. Each line will appended to a StringBuilder
 6           * and returned as String.
 7           */
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
               StringBuilder sb = new StringBuilder();

               String line = null;
             try {
                     while ((line = reader.readLine()) != null) {
                         sb.append(line + "\n");
                      }
                } catch (IOException e) {
                   e.printStackTrace();
                } finally {
                    try {
                          is.close();
                            } catch (IOException e) {
                              e.printStackTrace();
                            }
                     }

                return sb.toString();
          }

}
