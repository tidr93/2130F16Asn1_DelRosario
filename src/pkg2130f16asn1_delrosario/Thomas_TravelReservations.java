/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2130f16asn1_delrosario;
import java.util.Date;
import java.text.SimpleDateFormat;
/**
 *
 * @author Thomas
 */
public class Thomas_TravelReservations {
    
    private Date date;
    private Thomas_Flight flight;
    private Thomas_Passenger passenger;
    
    public void setDate(Date _date) { date = _date; }
    public Date getDate() { return date; }
    public Thomas_Flight getFlight() { return flight; }
    public Thomas_Passenger getPassenger() { return passenger; }
    // For regular passenger
    public Thomas_TravelReservations(Date _date, 
                              int _number, String _source, String _destination,
                              double _fare,
                              String _firstName, String _lastName, int _age) {
        
        date = _date;
        flight = new Thomas_Flight(_number, _source, _destination, _fare);
        passenger = new Thomas_Passenger(_firstName, _lastName, _age);

    }
    // For FF passengers
    public Thomas_TravelReservations(Date _date, 
                              int _number, String _source, String _destination,
                              double _fare,
                              String _firstName, String _lastName, int _age,
                              int _id, int _miles) {
        
        date = _date;
        flight = new Thomas_Flight(_number, _source, _destination, _fare);
        passenger = new Thomas_FFPassenger(_firstName, _lastName, _age, _id, _miles);
        
    }
    
    public Thomas_TravelReservations() {
        
        flight = new Thomas_Flight();
        passenger = new Thomas_Passenger();
        
    }
    
    public String toString(SimpleDateFormat format) {
        
        return "Travel Reservation Made Today at " + format.format(date) + ":\n" + 
               flight.toString() + "\n" + passenger.toString() + "\n";
        
    }
    
    public String toShortString(SimpleDateFormat format) {
        
        return format.format(date) + " - " + passenger.toShortString();
        
    }
    
}
