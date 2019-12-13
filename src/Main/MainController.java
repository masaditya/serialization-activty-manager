/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author adit
 */
public class MainController implements Runnable {

    MainGui view;
    SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy");
    Calendar cal = Calendar.getInstance();
    public static String filename = "todos.o";

    List<Todo> todos = new ArrayList<Todo>();

    MainController(MainGui view) {
        this.view = view;

        view.getAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String todo = view.getTodo().getText();
                
            }

        });
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static String getTime(Calendar cal) {
        return "" + cal.get(Calendar.HOUR_OF_DAY) + ":"
                + (cal.get(Calendar.MINUTE)) + ":" + cal.get(Calendar.SECOND);
    }
    
    

    public void simpanObject(List<Todo> todos) throws
            FileNotFoundException, IOException {
        FileOutputStream fout;
        fout = new FileOutputStream(filename);
        ObjectOutputStream oout = new ObjectOutputStream(fout);
        oout.writeObject(todos);
        System.out.println("Object berhasil disimpan.");
    }

    public void bacaObject() throws FileNotFoundException,
            IOException, ClassNotFoundException {
        ObjectInputStream ois;
        ois = new ObjectInputStream(new FileInputStream(filename));
        System.out.println("Object dibaca.");
//        this.listMobil.add((Mobil) ois.readObject())
        this.todos = (List<Todo>) ois.readObject();

    }

}
