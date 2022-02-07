import java.util.concurrent.Semaphore;

//Clase productor
public class Productor extends Thread{
	
	private BuferNoSincronizado ubicacionCompartida;
	private Semaphore mutex, SemPropio, SemOtroProceso;
	private String nombre;
	
	//Constructor
	public Productor(BuferNoSincronizado compartido, String nombre, Semaphore mutex, Semaphore SemPropio, Semaphore SemSiguiente)
	{
		super(nombre);
		ubicacionCompartida=compartido;
		this.mutex=mutex;
		this.SemPropio=SemPropio;
		this.SemOtroProceso=SemSiguiente;
		this.nombre=nombre;
	}
	
	//Guardar valores de 1 a 4
	public void run ()
	{
		for(int cuenta=1;cuenta<=4;cuenta++)
		{
			try{
				//intenta tomar un permiso de su monitor
				SemPropio.acquire();
				//intenta acceso a seccion critica
				mutex.acquire();
				//coloca valor en seccion critica y escribe en consola
				
				System.out.println(nombre+" ha producido Bufer="+ubicacionCompartida.establecer());
			}
			catch(Exception excepcion)
			{
				excepcion.printStackTrace();
			}
			//suelta semaforo de exclusion mutua
			mutex.release();
			//abre semaforo del hilo siguiente en orden para que pueda acceder
			//a la seccion crituca
			SemOtroProceso.release();
		}
	}
}
