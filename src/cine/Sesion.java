package cine;


public class Sesion {
	
	//atributos
	private String hora;
	private int[][] estadoAsientos;
	private int asientosDisponibles;
	private int sigIdCompra;
	
	//metodos
	public Sesion(String hora, int filas, int columnas){
		this.hora = hora;
		this.estadoAsientos = new int[filas][columnas];
		for (int i=0; i<filas; i++){
			for(int j=0; j<columnas; j++){
				estadoAsientos[i][j]=0;
			}
		}
		this.asientosDisponibles = filas*columnas;
		this.sigIdCompra = 1;
	}
	
	public void comprarEntrada(int fila, int columna){
		
		//el "if" es para cerificar que la fila y la columna pedidas son validas
		if(fila<=this.estadoAsientos.length && columna<=this.estadoAsientos[0].length){ 
			this.estadoAsientos[fila-1][columna-1]=this.sigIdCompra;
			this.sigIdCompra++;
			this.asientosDisponibles--;
		}
	}
	
	public int getIdEntrada(int fila, int columna){
		return estadoAsientos[fila-1][columna-1];
	}
	
	public int getButacasDisponiblesSesion(){
		return this.asientosDisponibles;
	}
	
	public String getHora(){
		return hora;
	}
	
	public char[][] getEstadoSesion(){ 
		int filas=this.estadoAsientos.length;
		int columnas=this.estadoAsientos[0].length;
		
		char[][] estadoSesion = new char[filas][columnas];
		
		for (int i=0; i<filas; i++){
			for(int j=0; j<columnas; j++){
				if(this.estadoAsientos[i][j]==0)
					estadoSesion[i][j]='O';
				else 
					estadoSesion[i][j]='#';
			}
		}
		
		return estadoSesion;
	}
	 
	public String recogerEntradas(int id){
	
		int filas=this.estadoAsientos.length;
		int columnas=this.estadoAsientos[0].length;
		
		String str=this.hora+"+";
		
		boolean idExiste=false; 
		/* el boolean nos permite verificar si encontramos al id en la matriz, 
		 * no podemos definir un string null al principio (antes del porque no podemos anadir la hora dentro del "for", sino va a aparecer mas de una vez
		 * eso es porque utilizamos un variable boolean que va a cambiar a true si encontremos al id 
		 */
		
		for (int i=0; i<filas; i++){
			for(int j=0; j<columnas; j++){
				if(this.estadoAsientos[i][j]==id){
					str += (i+1)+","+(j+1)+"+";
					idExiste=true;
				}
			}
		}
		
		//si no encontramos al id en la matriz, debemos devolver un null, entonces str va a ser null
		if (idExiste==false)
			str=null;
		
		return str;
	}
	
	public ButacasContiguas recomendarButacasContiguas(int noButacas){
		int filas=this.estadoAsientos.length;
		int columnas=this.estadoAsientos[0].length;
		
		boolean noButacasValid=true;
		
		int fila=0,columna=0;
		int counter=0;
		
		//verificamos si el numero de butacas contiguas pedido es disponible siguiente el numero total de las columnas y las butacas disponibles
		
		if(noButacas>columnas || noButacas>this.asientosDisponibles)
			noButacasValid=false;
		
		else{ 
			for(int i=(int)(filas+1)/2; i<filas && counter!=noButacas; i++){
				
				//si la fila cambia, no consideramos la ultima fila 
					counter=0;
					fila=0;
					columna=0;
					
				for(int j=columnas-1; j>=0 && counter!=noButacas; j--){
					if(this.estadoAsientos[i][j]==0){
						fila=(i+1);
						columna=(j+1);
						counter++;
					}
					else{
						fila=0;
						columna=0;
						counter=0;
					}
				}
			}
			
			if(counter!=noButacas){
				for (int i=(int)(filas+1)/2-1 ; i>=0 && counter!=noButacas; i--){
					
					counter=0;
					fila=0;
					columna=0;
					
					for(int j=columnas-1; j>=0 && counter!=noButacas; j--){
						if(this.estadoAsientos[i][j]==0){
							fila=(i+1);
							columna=(j+1);
							counter++;
						}
						else{
							fila=0;
							columna=0;
							counter=0;
						}		
					}
				}
			}
		}
		
		//Creamos el objeto butacas llamando al contructor con los valores de atributos a partir de l
		ButacasContiguas butacas=new ButacasContiguas(fila,columna,noButacas);
		
		/* si el numero de butacas contiguas libres pedidas no es valido o el numero de fila/coulumna es 0 (no encontramos al numero de butacas pedidas
		 * disponibles), entonces devolvemos null, significa que el objeto butacas debe ser null
		 */
		if(!noButacasValid || fila==0 || columna==0)
			butacas = null;
		
		return butacas;
				
	}
	
	public void comprarEntradasRecomendadas(ButacasContiguas butacas){
		
		//debemos verificar si el objeto dado no es null, sino , no podemos comprar a entradas
		if(butacas!=null){
			for(int i=butacas.getColumna()-1; i<butacas.getColumna()+butacas.getNoButacas()-1; i++){
				this.estadoAsientos[butacas.getFila()-1][i]=this.sigIdCompra;
				this.asientosDisponibles--;
			}
			this.sigIdCompra++;	
		}
	}

}
