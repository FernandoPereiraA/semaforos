import java.util.concurrent.Semaphore;


public class Consumidor extends Thread{
	
	private BuferNoSincronizado ubicacionCompartida;
	private Semaphore mutex, SemPropio, SemOtroProceso;
	private String nombre;
	
	public Consumidor(BuferNoSincronizado compartido, String nombre, Semaphore mutex, Semaphore SemPropio, Semaphore SemSiguiente)
	{
		super(nombre);
		ubicacionCompartida=compartido;
		this.mutex=mutex;
		this.SemPropio=SemPropio;
		this.SemOtroProceso=SemSiguiente;
		this.nombre=nombre;
	}
	
	public void run()
	{
		for(int cuenta=1;cuenta<=4;cuenta++)
		{
			try{
				//intenta tomar un permiso de su semáforo
				SemPropio.acquire();
				//ya tiene su permiso ahora intenta entrar en la sección crítica
				mutex.acquire();
				//coge el valor y lo escribe en la consola
				
				System.out.println(nombre+" ha consumido Bufer="+ubicacionCompartida.obtener());
				
			}
			catch(Exception excepcion){
				excepcion.printStackTrace();
			}
			//suelta el semaforo de la exclusion mutua
			mutex.release();
			//abre el semaforo del siguiente hilo para que pueda volver a producir
			SemOtroProceso.release();
		}
	}
}
