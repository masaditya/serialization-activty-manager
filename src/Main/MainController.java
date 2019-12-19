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
    public static String filename = "todos.out";

    private final List<Todo> listTodos;
    Todo todo = new Todo();
//    private final List<Todo> todos;

    MainController(TodoGUI view) {
        this.view = view;
        this.listTodos = new ArrayList<Todo>();

        view.getButtonAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String todoName = view.getTodo().getText();
                Date date = (Date) view.getTime().getValue();

                System.out.println(todoName);
                System.out.println(date);

                Todo todo = new Todo(todoName, date);
                listTodos.add(todo);
                System.out.println(todo.getTodo());

//                view.getTodo().setText("");
                updateTodoPane();
                view.getTodo().setText("");

            }
        });

        view.getButtonSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("button save di klik");
                try {
                    simpanObject();
                } catch (IOException ex) {
                    Logger.getLogger(TodoGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        view.getButtonBaca().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    ArrayList listTodos = Baca();
//            System.out.println(listMobil.get(0));
//            controller.addElement(mobil);
//                    updateTodoPane();
                } catch (IOException ex) {
                    Logger.getLogger(TodoGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TodoGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        this.view.getTimePane().setText(this.view.getTimePane().getText() + "\n" + listTodos.get(listTodos.size() - 1).getTime());
        this.view.getTodoPane().setText(this.view.getTodoPane().getText() + "\n" + listTodos.get(listTodos.size() - 1).getTodo());
    }
    
    void clearTodoPane(){
        this.view.getTodoPane().setText("");
        this.view.getTimePane().setText("");
    }

    public void simpanObject() throws IOException {
        Simpan(listTodos);
    }

    public void Simpan(List<Todo> todo) throws FileNotFoundException, IOException {
        System.out.println("Saving list");

        FileOutputStream fout = new FileOutputStream(filename);
        // Construct an object output stream
        ObjectOutputStream oout = new ObjectOutputStream(fout);
        // Write the object to the stream
        
        oout.writeObject(todo);
        System.out.println("Object berhasil disimpan.");
        fout.close();
        //mengosongkan scroll pane
        clearTodoPane();
    }

    public ArrayList<Todo> Baca() throws FileNotFoundException, IOException, ClassNotFoundException {
        System.out.println("membaca list kegiatan");

        FileInputStream fin = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fin);
        Object obj = in.readObject();
        return (ArrayList<Todo>) obj;
    }

    public void bacaObject() throws FileNotFoundException,
            IOException, ClassNotFoundException {
        ObjectInputStream ois;
        ois = new ObjectInputStream(new FileInputStream(filename));
        System.out.println("Object dibaca.");
//        this.todos = (List<Todo>) ois.readObject();

    }

}
