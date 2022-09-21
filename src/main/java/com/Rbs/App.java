package com.Rbs;


import com.Rbs.Controllers.LogInController;
import com.Rbs.Views.LogInView;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/***
 * This is the main class that starts the whole app
 */
public class App {
    //Instance Variables
    BlockingQueue queue;

    //Run the App inside of the constructor
    public App(){
        queue = new LinkedBlockingQueue();

        //Always call the LogInController to start the app and call its main loop
        LogInController start = new LogInController(queue,new LogInView(queue));
        start.mainLoop();
    }



    public static void main(String[] args) {
        App start = new App();
    }
}
//Shivam Amin | shivamamin4@gmail.com
