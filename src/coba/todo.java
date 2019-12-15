/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coba;

/**
 *
 * @author Bawazir
 */
 public class todo implements java.io.Serializable {
    private String nama;
    private String waktu;
    
    public todo(){}
    public todo(String waktu, String nama){
        this.waktu = waktu;
        this.nama = nama;
    }
        public String getWaktu(){
            return waktu;
        }
        public void setCode(String code) {
            this.waktu = code;
        }
        public String getNama() {
            return nama;
        }
        public void setNama(String nama) {
            this.nama = nama;
        }
       
        
}

