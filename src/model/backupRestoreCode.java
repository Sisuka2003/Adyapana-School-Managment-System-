/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author DELL
 */
public class backupRestoreCode {

    public String backupdb(String path) {
           String msg=null;
    
        try {
            Runtime runTIme=Runtime.getRuntime();
            Process p=runTIme.exec("C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe -uroot -p20030909 --port=3307  --add-drop-database -B shopmanager -r "+path);
            
            int processComplete=p.waitFor();
            System.out.println(processComplete);
            msg="Backing up success";
        } catch (Exception e) {
            e.printStackTrace();
            msg="backing up error";
        }
    
    return msg;
    }
    
     
    public String restoredb(String path){
    String msg=null;
        try {
            Runtime runTIme=Runtime.getRuntime();
            String[] restoreCmd=new String[]{"C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql.exe",
                                                                      "--user=root",
                                                                      "--password=20030909",
                                                                      "--port=3307",
                                                                      "-e",
                                                                      "source" +path};
            
            Process p=runTIme.exec(restoreCmd);
            int processComplete=p.waitFor();
            System.out.println(processComplete);
            msg="restore success";
        } catch (Exception e) {
            e.printStackTrace();
            msg="restore error";
        }
    
    return msg;
    }
    
}
