
public MongoDBManager{

        public DB getMongoDBConnection(){


        //Por defecto se conecta a 127.0.0.1:27017
        MongoClient cliente = new MongoClient( new ServerAddress( "localhost", 27017));

        //Recuperamos la base de datos.
        DB database = cliente.getDB("test");

        }

        public List<DBObject> getSearchCollection(){
        List<DBObject> searchs= new ArrayList<>();
        //Recuperamos los valores de la colección, previamente hemos introducido
        //unos valores desde la consola de mongo con db.things.save({name:"mongoDB"})
        DBCollection coleccion = database.getCollection("search");
        //Recuperamos el elemento
        DBCursor cursor = collection.find();
        while (cursor.hasNext()){
            DBObject obj=cursor.next();
        searchs.add(obj);
        }
        return searchs;
        }

        public void saveSearch(Search search){
        //Recuperamos los valores de la colección, previamente hemos introducido
        //unos valores desde la consola de mongo con db.things.save({name:"mongoDB"})
        DBCollection colecction = database.getCollection("search");
        collection.insert(gson.toJson(search));
        }




}