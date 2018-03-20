package rts;

public class TempHumCalc {

	public Boolean calHum(float fhum) {
		if(fhum < 45){
			return true;
		}else{
			return false;
		}
	}
	
	public Boolean calTemp(float ftemp) {
		if(ftemp > 33){
			return true;
		}else{
			return false;
		}
	}
}
