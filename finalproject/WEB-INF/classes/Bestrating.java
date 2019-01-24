import java.io.*;
public class Bestrating
{
String carname ;
String rating;


public  Bestrating(String carname,String rating)
{
	
	this.carname = carname ;
    this.rating = rating;
}


public String getCarname(){
 return carname;
}

public String getRating () {
 return rating;
}
}