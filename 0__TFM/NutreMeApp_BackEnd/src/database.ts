import mongoose from "mongoose";
const databaseUrl = process.env.MONGODB_URL || 'monsasdgodb://michael:123456@mongodb/nutriapp';

mongoose.connect(databaseUrl, {
    autoIndex: true,
  } as mongoose.ConnectOptions)
    .then( db => {
      console.log('Db is connected to', db.connection.host); 
    })
    .catch(error => console.error(error));
