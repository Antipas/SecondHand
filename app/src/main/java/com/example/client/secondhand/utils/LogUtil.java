package com.example.client.secondhand.utils;

import android.util.Log;




/**
 * 调试时用于日志打印
 */
public class LogUtil {


    public static void v(String tag, Object o){
        if(StaticVariable.Debug.isDebug){
            Log.v(tag, ""+o)  ;
        }
    }
    public static void d(String tag, Object o){
        if(StaticVariable.Debug.isDebug){
            Log.d(tag, ""+o)  ;
        }
    }
    public static void i(String tag, Object o){
        if(StaticVariable.Debug.isDebug){
            Log.i(tag, ""+o)  ;
        }
    }
    public static void w(String tag, Object o){
        if(StaticVariable.Debug.isDebug){
            Log.w(tag, ""+o)  ;
        }
    }
    public static void e(String tag, Object o){
        if(StaticVariable.Debug.isDebug){
            Log.e(tag, ""+o)  ;
        }
    }

}
