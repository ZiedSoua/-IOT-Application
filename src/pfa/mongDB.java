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
import org.bson.Document;

/**
 *
 * @author Zied
 */
public class mongDB {
    MongoClient cl ;
    MongoDatabase db;
    MongoCollection coll;
    public void connectDB()
    {
        cl = new MongoClient("localhost", 27017);
        db = cl.getDatabase("zied");
        System.out.println("successful Conection to DB");
       db.createCollection("Cardiorythme");
       System.out.println("successful creation of coll "+db.getCollection("Cardiorythme").count());
    }
    public void insertdocintoDB(String[] ch)
    {
        coll= db.getCollection("Cardiorythme");
        Document doc = new Document();
        doc.put("time", ch[0]);
        doc.put("i",ch[1]);
        doc.put("ii",ch[2]);
        doc.put("iii",ch[3]);
        doc.put("avr",ch[4]);
        doc.put("avl",ch[5]);
        doc.put("avf",ch[6]);
        doc.put("v1",ch[7]);
        doc.put("v2",ch[8]);
        doc.put("v3",ch[9]);
        doc.put("v4",ch[10]);
        doc.put("v5",ch[11]);
        doc.put("v6",ch[12]);
        coll.insertOne(doc);
    }
    public String find()
    {
        coll= db.getCollection("patient");
        FindIterable d = coll.find(eq("name","zied"));
        MongoCursor cur=d.iterator();
        String res=cur.next().toString();
        return (res);
    }
    
}
