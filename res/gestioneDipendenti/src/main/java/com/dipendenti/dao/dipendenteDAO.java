package com.dipendenti.dao;

import java.util.List;

import com.dipendenti.model.Dipendente;

public interface dipendenteDAO {
	void inserisci(Dipendente d);
	void aggiorna(Dipendente d);
	void elimina(int i);
	Dipendente cercaPerID(int id);
	List<Dipendente> getAllDipendenti();
}
