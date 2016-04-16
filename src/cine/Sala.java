package cine;


import anotacion.Programacion2; 

@Programacion2 (
	nombreAutor1 = "oumaima",
	apellidoAutor1 = "talouka",
	emailUPMAutor1 = "oumaima.talouka@alumnos.upm.es",
	enGrupo = false,
	nombreAutor2 = "",
	apellidoAutor2 = "", 
	emailUPMAutor2 = ""
)

public class Sala {

	//atributos
	private String pelicula;
	private Sesion[] sesiones;
	
	//metodos
	public Sala(String pelicula, String[] horasSesiones, int filas, int columnas){
		this.pelicula=pelicula;
		this.sesiones=new Sesion[horasSesiones.length];
		for(int i=0; i<horasSesiones.length; i++){
			this.sesiones[i]=new Sesion(horasSesiones[i], filas, columnas);
		}
	}
	
	public void comprarEntrada(int sesion, int fila, int columna){
		sesiones[sesion-1].comprarEntrada(fila, columna);
	}
	
	public int getIdEntrada(int sesion, int fila, int columna){
		return sesiones[sesion-1].getIdEntrada(fila, columna);
	}
	
	public String[] getHorasDeSesionesDeSala(){
		String[] horas=new String[sesiones.length];
		for(int i=0; i<horas.length; i++){
			horas[i]=sesiones[i].getHora();
		}
		return horas;
	}
	
	public char[][] getEstadoSesion(int sesion){
		return sesiones[sesion-1].getEstadoSesion();
	}
	
	public String getPelicula(){
		return this.pelicula;
	}
		
	public String recogerEntradas(int id, int sesion){
		
		String strSala=null;
		
		if(sesiones[sesion-1].recogerEntradas(id)!=null)
			strSala=this.pelicula+"@"+sesiones[sesion-1].recogerEntradas(id);
		
		return strSala;
	}
	
	public int getButacasDisponiblesSesion(int sesion){
		return sesiones[sesion-1].getButacasDisponiblesSesion();
	}
	
	public ButacasContiguas recomendarButacasContiguas(int noButacas, int sesion){
		return sesiones[sesion-1].recomendarButacasContiguas(noButacas);
	}
	
	public void comprarEntradasRecomendadas(int sesion, ButacasContiguas butacas){
		sesiones[sesion-1].comprarEntradasRecomendadas(butacas);
	}
	
}
