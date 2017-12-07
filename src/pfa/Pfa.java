/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfa;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

/**
 *
 * @author Zied
 */
public class Pfa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Trait a = new Trait(); 
         try {
            try{
            a.stockageHeartRate();
           }
            catch (FileNotFoundException e)
            {e.printStackTrace();
            System.out.println("erreur d'ouverture");
            }
            
        } catch (IOException ex) {
           ex.printStackTrace();
        }
        
        }
    }
    

