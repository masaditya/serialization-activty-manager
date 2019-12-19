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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adit
 */
public class MainController implements Runnable {

    TodoGUI view;
    SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy");
    Calendar cal = Calendar.getInstance();
    public static String filename = "todos.o";

    List<Todo> todos = new ArrayList<Todo>();
//    private final List<Todo> todos;

    MainController(TodoGUI view) {
        this.view = view;
//        this.todos = new ArrayList<Todo>();

        view.getButtonAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String todoName = view.getTodo().getText();
                Date date = (Date) view.getTime().getValue();

                System.out.println(todoName);
                System.out.println(date);

                Todo todo = new Todo(todoName, date);
                todos.add(todo);
                System.out.println(todos.get(0));

//                view.getTodo().setText("");
                updateTodoPane();
                view.getTodo().setText("");

            }
        });
        

    }

    @Override
    public void run() {
//        try {
//            bacaObject();
//        } catch (IOException ex) {
//            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public static String getTime(Calendar cal) {
        return "" + cal.get(Calendar.HOUR_OF_DAY) + ":"
                + (cal.get(Calendar.MINUTE)) + ":" + cal.get(Calendar.SECOND);
    }

    void updateTodoPane() {
        this.view.getTimePane().setText(this.view.getTimePane().getText() + "\n" + todos.get(todos.size() - 1).getTime());
        this.view.getTodoPane().setText(this.view.getTodoPane().getText() + "\n" + todos.get(todos.size() - 1).getTodo());
    }

    public void simpanObject() throws IOException {
//        FileOutputStream fout;
//        fout = new FileOutputStream(filename);
//        ObjectOutputStream oout = new ObjectOutputStream(fout);
//        oout.writeObject(todos);
//        System.out.println("Object berhasil disimpan.");
            Simpan(todos);
    }
    
    public void Simpan(List<Todo> todos) throws FileNotFoundException, IOException {
        System.out.println("Saving list");

        FileOutputStream fout = new FileOutputStream(filename);
        // Construct an object output stream
        ObjectOutputStream oout = new ObjectOutputStream(fout);
        // Write the object to the stream
        oout.writeObject(todos);
        System.out.println("Object berhasil disimpan.");
        fout.close();
    }

    public void bacaObject() throws FileNotFoundException,
            IOException, ClassNotFoundException {
        ObjectInputStream ois;
        ois = new ObjectInputStream(new FileInputStream(filename));
        System.out.println("Object dibaca.");
//        this.todos = (List<Todo>) ois.readObject();

    }

}
