/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.Date;

/**
 *
 * @author adit
 */
public class Todo implements java.io.Serializable {

    String todo;
    Date time;
    boolean status;

    Todo() {

    }

    Todo(String todo, Date time) {
        this.todo = todo;
        this.time = time;
        this.status = status;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Todo <code:" + todo + ", time: " + time + ", status: " + status + ">";
        //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
