/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2130f16asn1_delrosario;

/**
 *
 * @author Thomas
 */
public class Thomas_FFPassenger extends Thomas_Passenger {
    
    private int id;
    private int miles;
    
    public void setID(int _id) { id = _id; }
    public void setMiles(int _miles) { miles = _miles; }
    public int getID() { return id; }
    public int getMiles() { return miles; }
    
    public Thomas_FFPassenger() {
        
        super();
        
    }
    
    public Thomas_FFPassenger(String _firstName, String _lastName, int _age, int _id, int _miles) {
        
        super(_firstName, _lastName, _age);
        id = _id;
        miles = _miles;
        
    }
    
    @Override
    public String toString() {
        
        String output = super.toString() + "\nFrequent Flyer Details:\nID: " + id +
                        "\nFrequent Flyer Miles: " + miles;
        
        return output;
        
    }
    
}
