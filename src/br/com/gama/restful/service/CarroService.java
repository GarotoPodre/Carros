package br.com.gama.restful.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.gama.restful.daos.CarroDAO;
import br.com.gama.restful.modelo.Carro;

public class CarroService {
	private CarroDAO db=new CarroDAO();
	
	public List<Carro> getCarros(){
		try {
			List<Carro> carros=db.getCarro();
			return carros;
		}catch(SQLException e) {
			e.printStackTrace();
			return new ArrayList<Carro>();
		}
	}
	public Carro getCarroById(Long id) {
		try {
			return db.getCarroById(id);
		}catch(SQLException e) {
			return null;
		}
	}
	public boolean delete(Long id) {
		try {
			return db.delete(id);
		}catch(SQLException e) {
			return false;
		}
	}
	
	public boolean sabe(Carro carro) {
		try {
			db.save(carro);
			return true;
		}catch(SQLException e) {
			return false;
		}
	}
	
	public List<Carro> findByName(String name){
		try {
			return db.findByName(name);
		}catch(SQLException e) {
			return null;
		}
	}
}
