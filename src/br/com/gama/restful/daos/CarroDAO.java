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
			stmt=conn.prepareStatement("select *from carro");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Carro c=createCarro(rs);
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
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn=getConnection();
			if(c.getId()==null) {
				stmt=conn.prepareStatement("insert into carro(nome, desc, urlFoto, urlVideo,"
						+ " latitude, longitude, tipo) values(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			}else {
				stmt=conn.prepareStatement("update carro set nome=?, desc=?, url_foto=?, url_video=?, "
						+ "latitude=?, longitude=?, tipo=? where id=?");
			}
			stmt.setString(1, c.getNome());
			stmt.setString(1, c.getDescricao());
			stmt.setString(1, c.getUrlFoto());
			stmt.setString(1, c.getUrlVideo());
			stmt.setString(1, c.getLatitude());
			stmt.setString(1, c.getLongitude());
			stmt.setString(1, c.getTipo());
			if(c.getId()!=null) {
				stmt.setLong(8, c.getId());
			}
			int count=stmt.executeUpdate();
			if(count==0) {
				throw new SQLException("Erro ao inserir o carro");
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
