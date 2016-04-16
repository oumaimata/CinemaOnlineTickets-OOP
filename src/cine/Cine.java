package cine;


public class Cine {

	//atributos
	private String nombre;
	private Sala[] salas;
	
	//metodos
	public Cine(String nombre, Sala[] salas){
		this.nombre=nombre;
		this.salas = salas; // no necesita duplicar el vector porque si modificamos una informacion, queremos que la modificacion se hace en el objeto de sala tambien
		
	}
	
	public void comprarEntrada(int sala, int sesion, int fila, int columna){
		salas[sala-1].comprarEntrada(sesion, fila, columna);
	}
	
	public ButacasContiguas recomendarButacasContiguas(int noButacas, int sala, int sesion){
		return salas[sala-1].recomendarButacasContiguas(noButacas, sesion);
	}
	
	public int getIdEntrada(int sala, int sesion, int fila, int columna){
		return salas[sala-1].getIdEntrada(sesion, fila, columna);
	}
	
	public char[][] getEstadoSesion(int sala, int sesion){
		return salas[sala-1].getEstadoSesion(sesion);
	}
	
	public String[] getPeliculas(){
		String[] peliculas = new String[salas.length];
		for(int i=0; i<salas.length; i++){
			peliculas[i]=salas[i].getPelicula();
		}
		return peliculas;
	}
	
	public String[] getHorasDeSesionesDeSala(int sala){
		String[] horas = new String[salas[sala].getHorasDeSesionesDeSala().length];
		for(int i=0; i<salas[sala].getHorasDeSesionesDeSala().length; i++){
			horas[i]=(salas[sala].getHorasDeSesionesDeSala())[i];
		}
		return horas;
	}
	
	public String recogerEntradas(int id, int sala, int sesion){
		
		String strCine = null;
		
		if(salas[sala-1].recogerEntradas(id,sesion)!=null)
			strCine=this.nombre+"@"+salas[sala-1].recogerEntradas(id,sesion);
		
		return strCine;
	}
	
	public int getButacasDisponiblesSesion(int sala, int sesion){
		return salas[sala-1].getButacasDisponiblesSesion(sesion);
	}
	
	public void comprarEntradasRecomendadas(int sala, int sesion, ButacasContiguas butacas){
		salas[sala-1].comprarEntradasRecomendadas(sesion,butacas);
	}

}
