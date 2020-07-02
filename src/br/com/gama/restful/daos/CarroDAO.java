package br.com.gama.restful.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.gama.restful.modelo.Carro;

public class CarroDAO extends BaseDAO{
	
	public Carro getCarroById(Long id) throws SQLException{
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=getConnection();
			stmt=conn.prepareStatement("Select *from carro where id=?");
			stmt.setLong(1, id);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) {
				Carro c=createCarro(rs);
				rs.close();
				return c;
			}
		}finally {
			if(stmt !=null) {
				stmt.close();
			}
			if(conn !=null) {
				conn.close();
			}
		}
		return null;
	}

	public List<Carro>findByName(String name)throws SQLException{
		List<Carro> carros=new ArrayList<Carro>();
		Connection conn=null;
		PreparedStatement stmt=null;
		
		try {
			conn=getConnection();
			stmt=conn.prepareStatement("select *from carro where lower(nome) like ?");
			stmt.setString(1, "%" + name.toLowerCase()+'%');
			ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				Carro c=createCarro(rs);
				carros.add(c);
			}
			rs.close();
		}finally {
			if(stmt !=null) {
				stmt.close();
			}
			if(conn !=null) {
				conn.close();
			}
		}
		return carros;
	}
	public List<Carro> findByTipo(String tipo)throws SQLException{
		List<Carro>carros=new ArrayList<Carro>();
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=getConnection();
			stmt=conn.prepareStatement("select *from carro where tipo = ?");
			stmt.setString(1, tipo);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Carro c=createCarro(rs);
				carros.add(c);
			}
			rs.close();
		}finally {
			if(stmt !=null) {
				stmt.close();
			}
		}
		return carros;
	}
	
	public List<Carro>getCarro() throws SQLException{
		List<Carro>carros=new ArrayList<Carro>();
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=getConnection();
			System.out.println("Catalogo retornado :"+conn.getCatalog());
			stmt=conn.prepareStatement("select * from carro");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Carro c=createCarro(rs);
				carros.add(c);
			}
			rs.close();
		}finally {
			if(stmt !=null) {
				stmt.close();
			}
			if(conn !=null) {
				conn.close();
			}
		}
		return carros;
	}
	
	public Carro createCarro(ResultSet rs) throws SQLException{
		Carro c=new Carro();
		c.setId(rs.getLong("id"));
		c.setNome(rs.getString("nome"));
		c.setDescricao(rs.getString("descricao"));
		c.setUrlFoto(rs.getString("url_Foto"));
		c.setUrlVideo(rs.getString("url_Video"));
		c.setLatitude(rs.getString("latitude"));
		c.setLongitude(rs.getString("longitude"));
		c.setTipo(rs.getString("tipo"));
		
		return c;
	}
	
	public void save(Carro c) throws SQLException{
		System.out.println("CarrooDao - save "+c.toString());
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=getConnection();
			if(c.getId()==null) {
				System.out.println("id � nulo");
				stmt=conn.prepareStatement("insert into carro(nome, descricao, url_Foto, url_Video,"
						+ " latitude, longitude, tipo) values(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
				//System.out.println("id gerado "+i);
			}else {
				stmt=conn.prepareStatement("update carro set nome=?, descricao=?, url_foto=?, url_video=?, latitude=?, longitude=?,tipo=? where id=?");
			}
			//stmt.setLong(1, i+1);
				stmt.setString(1, c.getNome());
				stmt.setString(2, c.getDescricao());
				stmt.setString(3, c.getUrlFoto());
				stmt.setString(4, c.getUrlVideo());
				stmt.setString(5, c.getLatitude());
				stmt.setString(6, c.getLongitude());
				stmt.setString(7, c.getTipo());
				if(c.getId()!=null) {
					stmt.setLong(8, c.getId());	
				}
				int cont=stmt.executeUpdate();
				if(cont==0) {
					throw new SQLException("Erro ao inserir Carro");
				}
				if(c.getId()==null) {
					Long id=getGeneratedId(stmt);
					c.setId(id);
				}
		}finally {
			if(stmt !=null) {
				stmt.close();
			}
			if(conn !=null) {
				conn.close();
			}
		}
	}

	private static Long getGeneratedId(PreparedStatement stmt) throws SQLException{
		ResultSet rs=stmt.getGeneratedKeys();
		if(rs.next()) {
			Long id=rs.getLong(1);//rs não começa do 0?
			return id;
		}
		return 0L;
	}
	
	public boolean delete(Long id) throws SQLException{
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=getConnection();
			stmt=conn.prepareStatement("delete from carro where id=?");
			stmt.setLong(1, id);
			int count=stmt.executeUpdate();
			boolean ok=count > 0;
			return ok;
		}finally {
			if(stmt !=null) {
				stmt.close();
			}
			if(conn !=null) {
				conn.close();
			}
		}
	}
	
}
