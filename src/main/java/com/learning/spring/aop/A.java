package com.learning.spring.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {

    private static final Log logger = LogFactory.getLog(A.class);

    //传入进程名称processName
    public static boolean findAddKillProcess(String processName) {
        BufferedReader bufferedReader = null;
        try {
            Process proc = Runtime.getRuntime().exec("c:\\windows\\system32\\tasklist -fi " + '"' + "imagename eq " + processName +'"');
            bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                logger.info("findProcess()获取到的进程信息：" + line);
                if (line.contains(processName)) {
                    killProcess(processName);
                    logger.info("找到并且杀死进程：" + processName);
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception ex) {}
            }
        }
    }

    //通过进程名称杀死进程
    public static void killProcess(String processName) {
        try {
            if(processName != null) {
                Process pro = Runtime.getRuntime().exec("c:\\windows\\system32\\taskkill /F /im "+ processName);
                BufferedReader brStd = new BufferedReader(new InputStreamReader(pro.getInputStream()));
                BufferedReader brErr = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
                long time = System.currentTimeMillis();
                while (true) {
                    if (brStd.ready()) {
                        logger.info("killProcess()进程正常返回:" + processName);
                        break;
                    }
                    if (brErr.ready()) {
                        logger.info("killProcess()进程出错返回:" + processName);
                        break;
                    }
                    if(System.currentTimeMillis() - time > 3000) {
                        logger.info("killProcess()等待超时:" + processName);
                        return;
                    }
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void main(String[] args) {
        findAddKillProcess("msiexec");
    }
}
