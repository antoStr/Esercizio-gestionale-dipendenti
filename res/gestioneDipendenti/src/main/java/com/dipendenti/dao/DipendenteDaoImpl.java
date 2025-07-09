package com.dipendenti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dipendenti.model.Dipendente;

public class DipendenteDaoImpl implements dipendenteDAO {

	@Override
	public void inserisci(Dipendente d) {
		String querySql = "INSERT INTO dipendenti (nome, cognome, codice_fiscale, data_nascita, luogo_nascita, email, tel, indirizzo, data_assunzione, ruolo, reparto, stipendio, attivo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(querySql)){

			pstmt.setString(1, d.getNome());
			pstmt.setString(2, d.getCognome());
			pstmt.setString(3, d.getCodice_fiscale());
			pstmt.setDate(4, java.sql.Date.valueOf(d.getData_nascita()));
			pstmt.setString(5, d.getLuogo_nascita());
			pstmt.setString(6, d.getEmail());
			pstmt.setString(7, d.getTel());
			pstmt.setString(8, d.getIndirizzo());
			pstmt.setDate(9, java.sql.Date.valueOf(d.getData_assunzione()));
			pstmt.setString(10, d.getRuolo());
			pstmt.setString(11, d.getReparto());
			pstmt.setInt(12, d.getStipendio());
			pstmt.setBoolean(13, d.isAttivo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Errore durante l'inserimento del dipendente." + e.getStackTrace()) ;
		}

	}

	@Override
	public void aggiorna(Dipendente d) {
		String querySql = "UPDATE dipendenti SET nome = ?, cognome = ?, codice_fiscale = ?, data_nascita = ?, luogo_nascita= ?, email = ?, tel = ?, indirizzo = ?, data_assunzione = ?, ruolo = ?, reparto = ?, stipendio = ?, attivo = ? WHERE id = ?";

		try (Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(querySql)) {

			pstmt.setString(1, d.getNome());
			pstmt.setString(2, d.getCognome());
			pstmt.setString(3, d.getCodice_fiscale());
			pstmt.setDate(4, java.sql.Date.valueOf(d.getData_nascita()));
			pstmt.setString(5, d.getLuogo_nascita());
			pstmt.setString(6, d.getEmail());
			pstmt.setString(7, d.getTel());
			pstmt.setString(8, d.getIndirizzo());
			pstmt.setDate(9, java.sql.Date.valueOf(d.getData_assunzione()));
			pstmt.setString(10, d.getRuolo());
			pstmt.setString(11, d.getReparto());
			pstmt.setInt(12, d.getStipendio());
			pstmt.setBoolean(13, d.isAttivo());
			pstmt.setInt(14, d.getId());

			int righeAggiornate = pstmt.executeUpdate();
			
	        if (righeAggiornate > 0) {
	            System.out.println("Dipendente aggiornato con successo!");
	        } else {
	            System.out.println("Nessun dipendente trovato con quell'ID.");
	        }

		} catch (SQLException e) {
			System.err.println("Errore durante l'aggiornamento del dipendente.");
		}
	}

	@Override
	public void elimina(int i) {
		String querySql = "DELETE FROM dipendenti WHERE id = ?";
		
		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(querySql)){
			
			pstmt.setInt(1, i);
			
			int righeAggiornate = pstmt.executeUpdate();
			
	        if (righeAggiornate > 0) {
	            System.out.println("Dipendente aggiornato con successo!");
	        } else {
	            System.out.println("Nessun dipendente trovato con quell'ID.");
	        }
			
		} catch (SQLException e) {
			System.err.println("Errore durante l'eliminazione del dipendente.");
		}

	}

	@Override
	public Dipendente cercaPerID(int id) {
		String querySql = "SELECT * FROM dipendenti WHERE id = ?";
		Dipendente d = new Dipendente();
		
		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(querySql)) {
			
			pstmt.setInt(1, id);
			
			try (ResultSet rs = pstmt.executeQuery()){
				if (rs.next()) {
					int idDipendente = rs.getInt("id");
					String nomeDipendente = rs.getString("nome");
					String cognomeDipendente = rs.getString("cognome");
					String codiceFiscale = rs.getString("codice_fiscale");
					LocalDate dataNascita = rs.getDate("data_nascita").toLocalDate();
					String luogoNascita = rs.getString("luogo_nascita");
					String emailDipendente = rs.getNString("email");
					String telDipendente = rs.getString("tel");
					String indirizzo = rs.getNString("indirizzo");
					LocalDate dataAssunzione = rs.getDate("data_assunzione").toLocalDate();
					String reparto = rs.getNString("reparto");
					String ruolo = rs.getString("ruolo");
					int stipendio = rs.getInt("stipendio");
					boolean attivo = rs.getBoolean("attivo");
					
					d = new Dipendente (idDipendente, nomeDipendente, cognomeDipendente, codiceFiscale, dataNascita, luogoNascita, emailDipendente, telDipendente, indirizzo, dataAssunzione, ruolo, reparto, stipendio, attivo);
					System.out.println(d);
				}
			} 
		} catch (SQLException e) {
			System.err.println("Errore nella ricerca del dipendente.");
		}
		
		return d;
	}

	@Override
	public List<Dipendente> getAllDipendenti() {
		String querySql = "SELECT * FROM dipendenti";
		List<Dipendente> listaDipendenti = new ArrayList<>();
		Dipendente d = new Dipendente();
		
		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(querySql)){
			
			try(ResultSet rs = pstmt.executeQuery()){
				while ( rs.next()) {
					int idDipendente = rs.getInt("id");
					String nomeDipendente = rs.getString("nome");
					String cognomeDipendente = rs.getString("cognome");
					String codiceFiscale = rs.getString("codice_fiscale");
					LocalDate dataNascita = rs.getDate("data_nascita").toLocalDate();
					String luogoNascita = rs.getString("luogo_nascita");
					String emailDipendente = rs.getNString("email");
					String telDipendente = rs.getString("tel");
					String indirizzo = rs.getNString("indirizzo");
					LocalDate dataAssunzione = rs.getDate("data_assunzione").toLocalDate();
					String reparto = rs.getNString("reparto");
					String ruolo = rs.getString("ruolo");
					int stipendio = rs.getInt("stipendio");
					boolean attivo = rs.getBoolean("attivo");
					
					d = new Dipendente (idDipendente, nomeDipendente, cognomeDipendente, codiceFiscale, dataNascita, luogoNascita, emailDipendente, telDipendente, indirizzo, dataAssunzione, reparto, ruolo, stipendio, attivo);
					listaDipendenti.add(d);		
				}	
			}
		} catch (Exception e) {
			System.err.println("Errore nell'output dei dipendenti.");
		}
		
		System.out.println(listaDipendenti);
		return listaDipendenti;
	}

}
