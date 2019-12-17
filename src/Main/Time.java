/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.Date;

/**
 *
 * @author Bawazir
 */
public class Time {
    Date time;
    
    Time(Date time){
        this.time = time;
    }
      public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
