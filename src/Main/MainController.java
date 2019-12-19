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
import java.text.DateFormat;
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
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    Calendar cal = Calendar.getInstance();
    public static String filename = "todos.out";
    
    List<Todo> listTodos;
    
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
                System.out.println("todo yang sudah di add : " + todo.getTodo());
                
                updateTodoPane();
                view.getTodo().setText("");
                
            }
        });
        
        view.getButtonSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("button save di klik");
                try {
                    Simpan(listTodos);
                } catch (IOException ex) {
                    Logger.getLogger(TodoGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    @Override
    public void run() {
        try {
            view.getTodo().setText("");
            bacaObject();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while (true) {
            cal.add(Calendar.SECOND, +1);
            view.getCounter().setText(formatter.format(cal.getTime()));
            
            for (int i = 0; i < listTodos.size(); i++) {
                if (formatter.format(listTodos.get(i).getTime()).equals(formatter.format(cal.getTime()))) {
                    listTodos.get(i).setStatus(true);
                    updateTodoPane();
                }
            }
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static String getTime(Calendar cal) {
        return "" + cal.get(Calendar.HOUR_OF_DAY) + ":"
                + (cal.get(Calendar.MINUTE)) + ":" + cal.get(Calendar.SECOND);
    }
    
    void updateTodoPane() {
        clearTodoPane();
        for (int i = 0; i < listTodos.size(); i++) {
            Todo todo = listTodos.get(i);
            Date tmp = listTodos.get(i).getTime();
            this.view.getTimePane().setText(this.view.getTimePane().getText() + "\n" + formatter.format(tmp));
            this.view.getTodoPane().setText(this.view.getTodoPane().getText() + "\n" + todo.getTodo() + " (" + todo.isStatus()+")");
        }
    }
    
    void clearTodoPane() {
        this.view.getTodoPane().setText("");
        this.view.getTimePane().setText("");
    }
    
    public void Simpan(List<Todo> todo) throws FileNotFoundException, IOException {
        System.out.println("Saving list");
        FileOutputStream fout = new FileOutputStream(filename);
        ObjectOutputStream oout = new ObjectOutputStream(fout);
        oout.writeObject(todo);
        System.out.println("Object berhasil disimpan.");
        fout.close();
        clearTodoPane();
    }
    
    public void bacaObject() throws FileNotFoundException,
            IOException, ClassNotFoundException {
        ObjectInputStream ois;
        ois = new ObjectInputStream(new FileInputStream(filename));
        System.out.println("Object dibaca.");
        
        this.listTodos = (List<Todo>) ois.readObject();
        updateTodoPane();
        
    }
    
}
