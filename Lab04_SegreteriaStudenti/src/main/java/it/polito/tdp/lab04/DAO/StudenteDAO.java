package it.polito.tdp.lab04.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;


public class StudenteDAO {
	
	public Studente getNomeByMatricola (Studente studente) {
		
		String sql = "SELECT * "
		+ "FROM studente s "
		+ "where matricola = ?";
		
		Studente s=null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				s= new Studente (rs.getInt("matricola"),rs.getString("nome"),rs.getString("cognome"), rs.getString("CDS") );
			}
			rs.close();
			st.close();
			conn.close();
			
		}catch (SQLException e) {
			throw new RuntimeException();
		}
		
		return s;
	}

	public boolean esisteMatricola(Studente studente) {
		String sql = "SELECT * FROM Studente s where s.matricola=?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,studente.getMatricola());
			ResultSet rs = st.executeQuery(); 
			
			if (rs.next()) {
				rs.close();
				st.close();
				conn.close();
				return true;
			}
			else {
				rs.close();
				st.close();
				conn.close();
				return false;
			}
		}catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public List <Corso> getCorsiStudente (Studente studente) {
		String sql = "SELECT c.codins, c.crediti, c.nome, c.pd "
				+"FROM corso c, iscrizione i "
				+"where c.codins= i.codins AND i.matricola=?";
		
		List <Corso> corsi = new LinkedList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,studente.getMatricola());
			ResultSet rs = st.executeQuery(); 
			
			while (rs.next()) {
				Corso c= new Corso ( rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd") );
				corsi.add(c);
			}
			rs.close();
			st.close();
			conn.close();
		}catch (SQLException e) {
			throw new RuntimeException();
		}
		return corsi;
		
	}

}
