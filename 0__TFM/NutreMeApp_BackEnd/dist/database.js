"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const mongoose_1 = __importDefault(require("mongoose"));
const databaseUrl = process.env.MONGODB_URL || 'mongodb://michael:123456@mongodb/nutriapp';
mongoose_1.default.connect(databaseUrl, {
    useUnifiedTopology: true,
    useNewUrlParser: true,
})
    .then(db => {
    console.log('Db is connected to', db.connection.host);
})
    .catch(error => console.error(error));
