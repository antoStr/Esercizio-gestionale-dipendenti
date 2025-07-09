package com.dipendenti.model;

import java.time.LocalDate;

public class Dipendente {
	private int id;
	private String nome;
	private String cognome;
	private String codice_fiscale;
	private LocalDate data_nascita;
	private String luogo_nascita;
	private String email;
	private String tel;
	private String indirizzo;
	private LocalDate data_assunzione;
	private String ruolo;
	private String reparto;
	private int stipendio;
	private boolean attivo;
	
	public Dipendente() {
		
	}
	
	public Dipendente(int id, String nome, String cognome, String codice_fiscale, LocalDate data_nascita, String luogo_nascita,
			String email, String tel, String indirizzo, LocalDate data_assunzione, String ruolo, String reparto,
			int stipendio, boolean attivo) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codice_fiscale = codice_fiscale;
		this.data_nascita = data_nascita;
		this.luogo_nascita = luogo_nascita;
		this.email = email;
		this.tel = tel;
		this.indirizzo = indirizzo;
		this.data_assunzione = data_assunzione;
		this.ruolo = ruolo;
		this.reparto = reparto;
		this.stipendio = stipendio;
		this.attivo = attivo;
	}
	
	public Dipendente(String nome, String cognome, String codice_fiscale, LocalDate data_nascita, String luogo_nascita,
			String email, String tel, String indirizzo, LocalDate data_assunzione, String ruolo, String reparto,
			int stipendio, boolean attivo) {
		this.nome = nome;
		this.cognome = cognome;
		this.codice_fiscale = codice_fiscale;
		this.data_nascita = data_nascita;
		this.luogo_nascita = luogo_nascita;
		this.email = email;
		this.tel = tel;
		this.indirizzo = indirizzo;
		this.data_assunzione = data_assunzione;
		this.ruolo = ruolo;
		this.reparto = reparto;
		this.stipendio = stipendio;
		this.attivo = attivo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCodice_fiscale() {
		return codice_fiscale;
	}
	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = codice_fiscale;
	}
	public LocalDate getData_nascita() {
		return data_nascita;
	}
	public void setData_nascita(LocalDate data_nascita) {
		this.data_nascita = data_nascita;
	}
	public String getLuogo_nascita() {
		return luogo_nascita;
	}
	public void setLuogo_nascita(String luogo_nascita) {
		this.luogo_nascita = luogo_nascita;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public LocalDate getData_assunzione() {
		return data_assunzione;
	}
	public void setData_assunzione(LocalDate data_assunzione) {
		this.data_assunzione = data_assunzione;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public String getReparto() {
		return reparto;
	}
	public void setReparto(String reparto) {
		this.reparto = reparto;
	}
	public int getStipendio() {
		return stipendio;
	}
	public void setStipendio(int stipendio) {
		this.stipendio = stipendio;
	}
	public boolean isAttivo() {
		return attivo;
	}
	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "dipendente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", codice_fiscale=" + codice_fiscale
				+ ", data_nascita=" + data_nascita + ", luogo_nascita=" + luogo_nascita + ", email=" + email + ", tel="
				+ tel + ", indirizzo=" + indirizzo + ", data_assunzione=" + data_assunzione + ", ruolo=" + ruolo
				+ ", reparto=" + reparto + ", stipendio=" + stipendio + ", attivo=" + attivo + "]";
	}
	
	
}
