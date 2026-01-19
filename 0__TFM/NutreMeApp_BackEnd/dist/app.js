"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const dotenv = require('dotenv');
dotenv.config({ path: `${__dirname}/../config.env` });
require('./database');
const meals_routes_1 = __importDefault(require("./routes/meals.routes"));
const app = (0, express_1.default)();
const port = process.env.SERVER_PORT_NUMBER || 3000;
app.use(express_1.default.json());
app.listen(port, () => {
    console.log('listening at port 3000 on ' + (process.env.NODE_ENV) + ' mode');
});
app.use('/api/v1/meals', meals_routes_1.default);