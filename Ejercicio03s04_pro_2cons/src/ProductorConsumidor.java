import java.util.concurrent.Semaphore;


public class ProductorConsumidor {
	public static void main(String [] args)
	{
		//ubicacion compartida es la seccion critica
		BuferNoSincronizado ubicacionCompartida = new BuferNoSincronizado();
		
		//semaforo para controlar la exclusion muta es decir
		//el acceso a la seccion critica
		Semaphore mutex = new Semaphore(1,true);
		
		//semaforos para un productor y dos consumidores
		//solo esta con permisos el primer productor.
		Semaphore SemProductor1 = new Semaphore(1,true);
		Semaphore SemConsumidor1 = new Semaphore(0,true);
		Semaphore SemConsumidor2 = new Semaphore(0,true);
		
		//el productor abre el semaforo del primer consumidor
		//el primer consumidor el del segundo
		//el segundo consumidor abre de nuevo el semaforo del productor
		Productor productor1 = new Productor(ubicacionCompartida,"Productor", mutex, SemProductor1, SemConsumidor1);
		Consumidor consumidor1 = new Consumidor(ubicacionCompartida,"Consumidor1", mutex, SemConsumidor1, SemConsumidor2);
		Consumidor consumidor2 = new Consumidor(ubicacionCompartida,"Consumidor2", mutex, SemConsumidor2, SemProductor1);
		
		//se arrancan los hilos
		productor1.start();
		consumidor1.start();
		consumidor2.start();
	}
}
