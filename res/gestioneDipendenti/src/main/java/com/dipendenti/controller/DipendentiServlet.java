package com.dipendenti.controller;

import java.io.IOException;
import java.time.LocalDate;

import com.dipendenti.dao.DipendenteDaoImpl;
import com.dipendenti.model.Dipendente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DipendentiServlet
 */
@WebServlet("/DipendentiServlet")
public class DipendentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DipendentiServlet() {
        // TODO Auto-generated constructor stub 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action == null) {
			action = "showAll";
		}
		
		switch (action) {
		case "findId":
			cercaDipendente(request, response);
			break;
		case "showAll":
			mostraDipendenti(request, response);
			break;
		default:
			response.sendError(400, "Azione non valida");
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action == null) {
			action = "insert";
		}
		
		switch (action) {
		case "insert":
			inserisciDipendente(request, response);
			break;
		case "remove":
			rimuoviDipendente(request, response);
			break;
		case "update":
			modificaDipendente(request, response);
			break;
		default:
			response.sendError(400, "Azione non valida");
			break;
		}
	}
	
	private void inserisciDipendente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String codiceFiscale = request.getParameter("codice_fiscale");
		
		String dataDiNascitaStr = request.getParameter("data_nascita");
		LocalDate dataDiNascita = LocalDate.parse(dataDiNascitaStr);
		
		String luogoNascita = request.getParameter("luogo_nascita");
		String email = request.getParameter("email");
		String numeroTel = request.getParameter("tel");
		String indirizzo = request.getParameter("indirizzo");
		
		String dataAssunzioneStr = request.getParameter("data_assunzione");
		LocalDate dataAssunzione = LocalDate.parse(dataAssunzioneStr);
		
		String ruolo = request.getParameter("ruolo");
		String reparto = request.getParameter("reparto");
		
		String stipendioStr = request.getParameter("stipendio");
		int stipendio = Integer.parseInt(stipendioStr);
		
		String attivoStr = request.getParameter("attivo");
		boolean attivo = Boolean.parseBoolean(attivoStr);
		
		Dipendente d = new Dipendente(nome, cognome, codiceFiscale, dataDiNascita, luogoNascita, email, numeroTel, indirizzo, dataAssunzione, ruolo, reparto, stipendio, attivo);
		
		DipendenteDaoImpl dao = new DipendenteDaoImpl();
		dao.inserisci(d);
		
		response.sendRedirect("ReturnPage.html");
		
	}
	
	private void rimuoviDipendente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idDipendenteStr = request.getParameter("id");
		int idDipendente = Integer.parseInt(idDipendenteStr);
		
		DipendenteDaoImpl dao = new DipendenteDaoImpl();
		dao.elimina(idDipendente);
		
		response.sendRedirect("ReturnPage.html");
	}
	
	private void modificaDipendente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String codiceFiscale = request.getParameter("codice_fiscale");
		
		String dataNascitaStr = request.getParameter("data_nascita");
		LocalDate dataNascita = LocalDate.parse(dataNascitaStr);
		
		String luogoNascita = request.getParameter("luogo_nascita");
		String email = request.getParameter("email");
		
		String tel = request.getParameter("tel");
		String indirizzo = request.getParameter("indirizzo");
		
		String dataAssunzioneStr = request.getParameter("data_assunzione");
		LocalDate dataAssunzione = LocalDate.parse(dataAssunzioneStr);
		
		String ruolo = request.getParameter("ruolo");
		String reparto = request.getParameter("reparto");
		
		String stipendioStr = request.getParameter("stipendio");
		int stipendio = Integer.parseInt(stipendioStr);
		
		String attivoStr = request.getParameter("attivo");
		boolean attivo = Boolean.parseBoolean(attivoStr);
		
		Dipendente d = new Dipendente(id, nome, cognome, codiceFiscale, dataNascita, luogoNascita, email, tel, indirizzo, dataAssunzione, ruolo, reparto, stipendio, attivo);
		
		DipendenteDaoImpl dao = new DipendenteDaoImpl();
		dao.aggiorna(d);
		
		response.sendRedirect("ReturnPage.html");
		
	}
	
	private void cercaDipendente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		
		DipendenteDaoImpl dao = new DipendenteDaoImpl();
		dao.cercaPerID(id); 
		
		response.sendRedirect("ReturnPage.html");
		
	}
	
	private void mostraDipendenti(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DipendenteDaoImpl dao = new DipendenteDaoImpl();
		dao.getAllDipendenti();
		
		response.sendRedirect("ReturnPage.html");
	}
	

}
