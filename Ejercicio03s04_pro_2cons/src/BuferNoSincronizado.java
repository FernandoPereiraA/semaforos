//Clase que representa un solo entero compartido
public class BuferNoSincronizado implements Bufer {
	private int bufer =0;

	
	@Override
	public int establecer() 
	{
		bufer+=2;
		return bufer;
	}

	@Override
	public int obtener() 
	{
		bufer--;
	    return bufer;  
	}
	
	}
