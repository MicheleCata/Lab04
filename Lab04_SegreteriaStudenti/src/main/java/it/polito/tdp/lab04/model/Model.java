package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	
	public Model(){
		corsoDao= new CorsoDAO();
		studenteDao= new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi(){
		return corsoDao.getTuttiICorsi();
	}

	public Studente getStudenteByMatricola(Integer matricola) {
		return studenteDao.getNomeByMatricola(new Studente(matricola, null, null, null));
	}
	
	public Corso getCorso(String codice){
		return corsoDao.getCorso(new Corso(codice, null, null,null));
	}

	public boolean esisteMatricola(Integer matricola) {
		return studenteDao.esisteMatricola(new Studente(matricola, null, null, null));
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(String codice){
		return corsoDao.getStudentiIscrittiAlCorso(new Corso(codice, null, null, null));
	}
	
	public List<Corso> getCorsiStudente (Integer matricola) {
		return studenteDao.getCorsiStudente(new Studente(matricola,null, null, null));
	}
}
