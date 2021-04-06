package it.polito.tdp.lab04.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				
				Corso c= new Corso (codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(c);
			}
			corsi.add(null);
			rs.close();
			st.close();
			conn.close();
		
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		return corsi;
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(Corso corso) {
		
		String sql = "SELECT * FROM corso where codins=?";
		Corso c=null;
		try {
			Connection conn =ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,corso.getCodins());
			ResultSet rs = st.executeQuery(); 
			
			if (rs.next()) {
				 c= new Corso ( rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd") );
			}
		}catch (SQLException e) {
			throw new RuntimeException();
		}
		return c;
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List <Studente> getStudentiIscrittiAlCorso(Corso corso) {
			
			String sql = "SELECT s.matricola, s.nome, s.cognome, s.CDS "
					+"FROM studente s, iscrizione i "
					+"where s.matricola = i.matricola AND i.codins = ?";
			
			List<Studente> result = new LinkedList<>();
			
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1,corso.getCodins());
				ResultSet rs = st.executeQuery(); 
				
				while (rs.next()) {
					Studente s = new Studente (rs.getInt("matricola"),rs.getString("nome"), rs.getString("cognome"), rs.getString("CDS"));
					result.add(s);
				}
				
				rs.close();
				st.close();
				conn.close();
					
			}catch (SQLException e) {
				throw new RuntimeException();
			}
			
			return result;
		}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}
