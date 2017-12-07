/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfa;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

/**
 *
 * @author Zied
 */
public class Trait {
    FileReader f;
    BufferedReader br;
    mongDB db ;
    
    
    public void stockageHeartRate() throws FileNotFoundException, IOException {
        db = new mongDB();
        this.f = new FileReader(new File("/home/pi/samples.txt"));
        this.br = new BufferedReader (f);
        String line ;
        this.db.connectDB();
        boolean com=true;
        int freqcard=0;
        while((line=br.readLine())!= null )
            {
                
                if(!line.contains("Elapsed time") && !line.contains("mV"))
                {  
                   String[] ch= line.split("	 ");
                   String time=ch[0];
                   double v1 = Double.parseDouble(ch[7]);
                   if((v1>1) && com==true)
                   {
                       freqcard++;
                       com=false;
                   }
                   if(v1<-0.001 && com==false)
                   {
                       com=true;
                   }
                   //System.out.println(time+"    "+v1+"  "); 
                   db.insertdocintoDB(ch);
                   freqcard=this.Notif(time, freqcard);
                   
                }
            }
        System.out.println("Stockage terminé");
    }
    
    
    public int Notif(String time , int freq)
    {
        SendMail mail = new SendMail();
        Vector<String> info = this.findinfoPatient();
        double age = Double.parseDouble(info.get(1));
        String[] m = time.split(":");
        double sec = Double.parseDouble(m[1]);
        double min=Double.parseDouble(m[0]);
        if(sec==0 && min!=0)
        {
            if((freq< 80) && (freq> 40))
            {
                //mail.sendmed(info.get(0),age,info.get(2),freq);
                System.out.println("Heart rate is normal: "+freq);
            }
            if(freq>=80)
            {
                mail.sendmed(info.get(0),age,info.get(2),freq);
                mail.sendneig(info.get(0),age,info.get(3),freq);
                System.out.println("sending mail to doctor and neighbors because fr>80: "+freq);
            }
            if(freq<=40)
            {
                mail.sendmed(info.get(0),age,info.get(2),freq);
                mail.sendneig(info.get(0),age,info.get(3),freq);
                System.out.println("sending mail to doctor and neighbors because fr<40: "+freq);
            }
            
            return 0;    
        }
        return freq;
    }
    
    
    
    public Vector<String> findinfoPatient()
    {
        
        String res = db.find();
        String[] champ=res.split(",");
        String[] cnom=champ[1].split("=");
        String[] cage=champ[2].split("=");
        String[] cmailmed=champ[3].split("=");
        String[] cmailneig=champ[4].split("=");
        Vector<String> info = new Vector<String>();
        String mailneig = cmailneig[1].replace("}}","");
        info.add(cnom[1]);
        info.add(cage[1]);
        info.add(cmailmed[1]);
        info.add(mailneig);
        //System.out.println(info.get(0)+info.get(1)+info.get(2)+info.get(3));
        return info ;
    }
}
       

