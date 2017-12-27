package io.ezorrio.md5;

import io.ezorrio.md5.manager.WindowManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    private static WindowManager mWindowManager;
    private static AppPrefs mPrefs;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mPrefs = new AppPrefs();
        mWindowManager = new WindowManager(primaryStage);
        mWindowManager.showMD5Scene();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static WindowManager getWindowManager() {
        return mWindowManager;
    }

    public static AppPrefs getPrefs() {
        return mPrefs;
    }
}
