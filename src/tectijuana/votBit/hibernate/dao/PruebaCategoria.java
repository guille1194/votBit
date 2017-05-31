package tectijuana.votBit.hibernate.dao;

import java.util.List;

import tectijuana.votBit.hibernate.Categoria;
import tectijuana.votBit.hibernate.util.HibernateUtil;

import org.apache.log4j.BasicConfigurator;

public class PruebaCategoria {

	public static void main(String[] args) 
	{
		BasicConfigurator.configure();
		HibernateUtil.verificarFabricaSesion();
		PruebasCategoria();
		
	}
	
	public static void Desplegar(String texto)
	{
		System.out.println(texto);
	}
	
	public static void PruebasCategoria()
	{
		Categoria categoria = new Categoria();
		//categoria.setId(2);
		//categoria.setNombre("Java");
		
		CategoriaDao.guardarCategoria(categoria);
		
		List<Categoria> listaCategoria = CategoriaDao.obtenerCategoria();
		if(listaCategoria == null) {
			System.out.println("No data in table");
		}
		Desplegar(listaCategoria.toString());
		
		
		//CategoriaDao.borrarCategoria(categoria);
		//Desplegar(CategoriaDao.obtenerCategoria(categoria.getId()).toString());
		
		//categoria.setNombre("Java");
		//CategoriaDao.actualizarCategoria(categoria);
	}

}
