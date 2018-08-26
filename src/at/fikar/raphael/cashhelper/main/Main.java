package at.fikar.raphael.cashhelper.main;

import at.fikar.raphael.cashhelper.gui.MainGUI;
import at.fikar.raphael.cashhelper.gui.javafx.Gui;
import at.fikar.raphael.cashhelper.injection.DALModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.awt.EventQueue;

public class Main {

    private static Injector injector;

    public static void main(final String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                final MainGUI frame = new MainGUI();
//                frame.setVisible(true);
//            } catch (final Exception e) {
//                e.printStackTrace();
//            }
//        });
        injector = bindModules();
        Gui gui = injector.getInstance(Gui.class);
        gui.launch(args);
    }

    private static Injector bindModules(){
        return Guice.createInjector(new DALModule());
    }
}
