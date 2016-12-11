package dao;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.util.JSON;
import domain.Search;

import java.util.ArrayList;
import java.util.List;

public  class MongoDBManager {

    public static DB getMongoDBConnection() {


        //Por defecto se conecta a 127.0.0.1:27017
        MongoClient cliente = new MongoClient(new ServerAddress("localhost", 27017));

        //Recuperamos la base de datos.
        DB database = cliente.getDB("local");
        return database;
    }

    public static List<DBObject> getSearchCollection() {
        List<DBObject> searchs = new ArrayList<>();
        //Recuperamos los valores de la colección, previamente hemos introducido
        //unos valores desde la consola de mongo con db.things.save({name:"mongoDB"}
        DB database = getMongoDBConnection();
        DBCollection collection = database.getCollection("search");
        //Recuperamos el elemento
        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
            DBObject obj = cursor.next();
            searchs.add(obj);
        }
        return searchs;
    }

    public static void saveSearch(Search search) {
        //Recuperamos los valores de la colección, previamente hemos introducido
        //unos valores desde la consola de mongo con db.things.save({name:"mongoDB"})
        DB database = getMongoDBConnection();
        DBCollection collection = database.getCollection("search");
        final Gson gson = new Gson();
        collection.insert((DBObject) JSON.parse(gson.toJson(search)));
    }


}