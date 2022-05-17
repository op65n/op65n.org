# [op65n.org](https://op65n.org)

This repository contains source code for op65n.org website, it's not really designed to be used
for others, but it's open sourced if you want to take a look

To build the frontend and backend run `gralde bundle`, jar file will be in the `./backend/build/libs` 
directory

### Frontend in `./fronntend`

Build using Vue3 and Vite 

`npm install` to install all dependencies (you need to run this only once)

`npm run dev` to start dev server

`npm run build && npm run preview` to build the app and start a production environment

### Backend in `./backend`

Build using Javalin

`gradle jar` to compile the backend

Go to `/build/libs` directory and run `java -jar op65n.org.jar`

To list all cli arguments run `java -jar op65n.org.jar --help`